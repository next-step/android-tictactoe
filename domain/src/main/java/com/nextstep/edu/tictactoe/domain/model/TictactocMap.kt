package com.nextstep.edu.tictactoe.domain.model

import com.nextstep.edu.tictactoe.domain.DefaultTictactoe.Companion.MAP_SIZE

class TictactocMap {

    private var map: Array<Array<Turn>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
    private var isFinish: Boolean = false

    fun resetMap() {
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
        isFinish = false
    }

    fun getGameResultFromSetMapPoint(point: Point, turn: Turn): GameResult {
        map[point.row][point.column] = turn
        return getGameResult(point = point, map = map, currentTurn = turn)
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

    private fun getGameResult(
        point: Point,
        map: Array<Array<Turn>>,
        currentTurn: Turn
    ): GameResult {
        val row = point.row
        val column = point.column

        var rowSum = 0
        var columnSum = 0

        for (point in 0 until MAP_SIZE) {
            rowSum += if (map[row][point] == currentTurn) 1 else 0
            columnSum += if (map[point][column] == currentTurn) 1 else 0
        }

        var leftDiagonalSum = 0
        var rightDiagonalSum = 0

        for (point in 0 until MAP_SIZE) {
            leftDiagonalSum += if (map[point][point] == currentTurn) 1 else 0
            rightDiagonalSum += if (map[point][MAP_SIZE - point - 1] == currentTurn) 1 else 0
        }

        val existWinner = listOf(rowSum, columnSum, leftDiagonalSum, rightDiagonalSum).contains(MAP_SIZE)

        var emptyBlock = 0
        for (i in 0 until 3) {
            emptyBlock += map[i].filter { it == Turn.UNKNOWN }.size
        }

        val result =
            if (existWinner && currentTurn == Turn.X) {
                isFinish = true
                GameResult.X_WIN
            } else if (existWinner && currentTurn == Turn.O) {
                isFinish = true
                GameResult.O_WIN
            } else if (!existWinner && emptyBlock == 0) {
                isFinish = true
                GameResult.TIE
            } else {
                isFinish = false
                GameResult.UNKNOWN
            }
        return result
    }
}