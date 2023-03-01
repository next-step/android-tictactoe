package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.GameResult
import camp.nextstep.edu.tictactoe.domain.model.Point
import camp.nextstep.edu.tictactoe.domain.model.Status
import camp.nextstep.edu.tictactoe.domain.model.Turn

class GameResultManager {

    fun getTurnResult(
        point: Point,
        map: Array<Array<Turn>>,
        currentTurn: Turn,
        count: Int
    ): Pair<GameResult, Status> {
        val r = point.row
        val c = point.column

        var rSum = 0
        var cSum = 0
        for (k in 0 until Ticktacktoe.MAP_SIZE) {
            rSum += if (map[r][k] == currentTurn) 1 else 0
            cSum += if (map[k][c] == currentTurn) 1 else 0
        }
        var leftDiagonalSum = 0
        var rightDiagonalSum = 0
        for (k in 0 until Ticktacktoe.MAP_SIZE) {
            leftDiagonalSum += if (map[k][k] == currentTurn) 1 else 0
            rightDiagonalSum += if (map[k][Ticktacktoe.MAP_SIZE - k - 1] == currentTurn) 1 else 0
        }

        val existWinner =
            listOf(rSum, cSum, leftDiagonalSum, rightDiagonalSum).contains(Ticktacktoe.MAP_SIZE)
        val result =
            if (existWinner && currentTurn == Turn.X) GameResult.X_WIN
            else if (existWinner && currentTurn == Turn.O) GameResult.O_WIN
            else if (!existWinner && (count == Ticktacktoe.MAP_SIZE * Ticktacktoe.MAP_SIZE)) {
                GameResult.TIE
            } else GameResult.UNKNOWN
        return Pair(result, Status(point, currentTurn))
    }

}