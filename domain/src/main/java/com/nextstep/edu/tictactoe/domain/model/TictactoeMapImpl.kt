package com.nextstep.edu.tictactoe.domain.model

import com.nextstep.edu.tictactoe.domain.Tictactoe.Companion.MAP_SIZE
import javax.inject.Inject

internal class TictactoeMapImpl @Inject constructor() : TictactoeMap {

    private var map: Array<Array<Turn>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }

    private var isFinish: Boolean = false
    private var currentTurn: Turn = Turn.X

    override fun resetMap() {
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
        isFinish = false
        currentTurn = Turn.X
    }

    override fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O else Turn.X
    }

    override fun getMapRowColumn(row: Int, column: Int): Turn = map[row][column]

    override fun getMap(): Array<Array<Turn>> = map

    override fun validData(point: Point): Boolean = !(map[point.row][point.column] != Turn.UNKNOWN || isFinish)

    override fun getIsFinish(): Boolean = isFinish

    override fun getCurrentTurn(): Turn = currentTurn

    override fun getNextPutPointsFromBehavior(behavior: Behavior): List<Point> {
        val interceptors = ArrayList<Point>()
        interceptors.addInterceptor(lines = Lines.leftDiagonal, behavior = behavior)
        interceptors.addInterceptor(lines = Lines.rightDiagonal, behavior = behavior)

        for (index in 0 until 3) {
            interceptors.addInterceptor(lines = Lines.row(rowIndex = index), behavior = behavior)
            interceptors.addInterceptor(lines = Lines.column(columnIndex = index), behavior = behavior)
        }

        return interceptors.toList()
    }

    override fun getGameResultFromSetMapPoint(
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

    private fun ArrayList<Point>.addInterceptor(lines: Lines, behavior: Behavior) {
        isRandomNextBehavior(lines).let {
            if (it.behavior == behavior) this.add(it.point)
        }
    }

    private fun isRandomNextBehavior(lines: Lines): RandomBehavior {
        var lineSum = 0
        var putPoint = Point.of(0, 0)
        for (index in 0 until 3) {
            getMapTurnFromLines(lines = lines, index = index).let { turn ->
                when (turn) {
                    Turn.UNKNOWN -> putPoint = getPointFromLines(lines = lines, index = index)
                    Turn.O -> lineSum += 1
                    Turn.X -> lineSum -= 1
                }
            }
        }

        val randomBehavior = when (lineSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }

        return randomBehavior
    }

    private fun getMapTurnFromLines(lines: Lines, index: Int): Turn {
        val turn = when (lines) {
            is Lines.row -> map[lines.rowIndex][index]
            is Lines.column -> map[index][lines.columnIndex]
            is Lines.leftDiagonal -> map[index][index]
            is Lines.rightDiagonal -> map[index][2-index]
        }

        return turn
    }

    private fun getPointFromLines(lines: Lines, index: Int): Point {
        val point = when(lines) {
            is Lines.row -> Point.of(row = lines.rowIndex, column = index)
            is Lines.column -> Point.of(row = index, column = lines.columnIndex)
            is Lines.rightDiagonal, Lines.leftDiagonal -> Point.of(row = index, column = index)
        }

        return point
    }
}