package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Status
import com.nextstep.edu.tictactoe.domain.model.Turn

class GameResultManager {

    fun getTurnResult(
        point: Point,
        map: Array<Array<Turn>>,
        currentTurn: Turn,
        count: Int
    ): Pair<GameResult, Status> {
        val row = point.row
        val column = point.column

        var rowSum = 0
        var columnSum = 0

        for (point in 0 until Tictactoe.MAP_SIZE) {
            rowSum += if (map[row][point] == currentTurn) 1 else 0
            columnSum += if (map[point][column] == currentTurn) 1 else 0
        }

        var leftDiagonalSum = 0
        var rightDiagonalSum = 0

        for (point in 0 until Tictactoe.MAP_SIZE) {
            leftDiagonalSum += if (map[point][point] == currentTurn) 1 else 0
            rightDiagonalSum += if (map[point][Tictactoe.MAP_SIZE - point - 1] == currentTurn) 1 else 0
        }

        val existWinner = listOf(rowSum, columnSum, leftDiagonalSum, rightDiagonalSum).contains(Tictactoe.MAP_SIZE)

        val result =
            if (existWinner && currentTurn == Turn.X) {
                GameResult.X_WIN
            } else if (existWinner && currentTurn == Turn.O) {
                GameResult.O_WIN
            } else if (!existWinner && (count == Tictactoe.MAP_SIZE * Tictactoe.MAP_SIZE)) {
                GameResult.TIE
            } else {
                GameResult.UNKNOWN
            }
        return Pair(result, Status(point, currentTurn))
    }

}