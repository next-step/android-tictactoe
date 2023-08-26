package com.nextstep.edu.tictactoe.domain.model

import com.nextstep.edu.tictactoe.domain.DefaultTictactoe.Companion.MAP_SIZE

class TictactocMap {

    private var map: Array<Array<Turn>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }

    private var isFinish: Boolean = false
    private var currentTurn: Turn = Turn.X

    fun resetMap() {
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
        isFinish = false
        currentTurn = Turn.X
    }

    fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O else Turn.X
    }

    fun getMapRowColumn(row: Int, column: Int): Turn {
        return map[row][column]
    }

    fun getMap(): Array<Array<Turn>> {
        return map
    }

    fun validData(point: Point): Boolean {
        return !(map[point.row][point.column] != Turn.UNKNOWN || isFinish)
    }

    fun getIsFinish(): Boolean {
        return isFinish
    }

    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    fun getGameResultFromSetMapPoint(
        point: Point,
    ): GameResult {
        setMapCurrentTurn(point = point)

        val (rowSum, columnSum) = getRowColumnSum(point = point)
        val (leftDiagonalSum, rightDiagonalSum) = getDiagonalSum()

        return getGameResult(rowSum = rowSum, columnSum = columnSum, leftDiagonalSum = leftDiagonalSum, rightDiagonalSum = rightDiagonalSum)
    }

    private fun setMapCurrentTurn(point: Point) {
        map[point.row][point.column] = currentTurn
    }

    private fun getRowColumnSum(point: Point): Pair<Int, Int> {
        var rowSum = 0
        var columnSum = 0

        for (index in 0 until MAP_SIZE) {
            rowSum += if (map[point.row][index] == currentTurn) 1 else 0
            columnSum += if (map[index][point.column] == currentTurn) 1 else 0
        }
        return Pair(rowSum, columnSum)
    }

    private fun getDiagonalSum(): Pair<Int, Int> {
        var leftDiagonalSum = 0
        var rightDiagonalSum = 0

        for (index in 0 until MAP_SIZE) {
            leftDiagonalSum += if (map[index][index] == currentTurn) 1 else 0
            rightDiagonalSum += if (map[index][MAP_SIZE - index - 1] == currentTurn) 1 else 0
        }

        return Pair(leftDiagonalSum, rightDiagonalSum)
    }

    private fun getEmptyBlockCount(): Int {
        var emptyBlock = 0
        for (i in 0 until 3) {
            emptyBlock += map[i].filter { it == Turn.UNKNOWN }.size
        }
        return emptyBlock
    }

    private fun getGameResult(
        rowSum: Int,
        columnSum: Int,
        leftDiagonalSum: Int,
        rightDiagonalSum: Int
    ): GameResult {
        val existWinner = listOf(rowSum, columnSum, leftDiagonalSum, rightDiagonalSum).contains(MAP_SIZE)
        val emptyBlock = getEmptyBlockCount()

        isFinish = (existWinner || (emptyBlock == 0))

        return if (existWinner && currentTurn == Turn.X) GameResult.X_WIN
        else if (existWinner && currentTurn == Turn.O) GameResult.O_WIN
        else if (!existWinner && emptyBlock == 0) GameResult.TIE
        else GameResult.UNKNOWN
    }

    fun getNextPutPointsFromBehavior(behavior: Behavior): List<Point> {
        val interceptorList = ArrayList<Point>()
        isLeftDialogBehavior().let {
            if (it.behavior == behavior) interceptorList.add(it.point)
        }
        isRightDialogBehavior().let {
            if (it.behavior == behavior) interceptorList.add(it.point)
        }

        for (index in 0 until 3) {
            isRowBehavior(index).let {
                if (it.behavior == behavior) interceptorList.add(it.point)
            }
            isColumnBehavior(index).let {
                if (it.behavior == behavior) interceptorList.add(it.point)
            }
        }

        return interceptorList.toList()
    }

    private fun isLeftDialogBehavior(): RandomBehavior {
        var dialogSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = point)
                Turn.O -> dialogSum += 1
                Turn.X -> dialogSum -= 1
            }
        }

        return when (dialogSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isRightDialogBehavior(): RandomBehavior {
        var dialogSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][2-point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = point)
                Turn.O -> dialogSum += 1
                Turn.X -> dialogSum -= 1
            }
        }

        return when (dialogSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isColumnBehavior(column: Int): RandomBehavior {
        var columnSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][column]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = column)
                Turn.O -> columnSum += 1
                Turn.X -> columnSum -= 1
            }
        }

        return when (columnSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isRowBehavior(row: Int): RandomBehavior {
        var columnSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[row][point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = row, column = point)
                Turn.O -> columnSum += 1
                Turn.X -> columnSum -= 1
            }
        }

        return when (columnSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }
}