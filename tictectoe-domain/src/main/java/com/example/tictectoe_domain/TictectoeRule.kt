package com.example.tictectoe_domain


class TictectoeRule {
    fun checkGameStatus(board: Board): GameStatus {
        board.lines.forEach {line ->
            if(line.count { cell -> cell == Cell.PLAYER1(cell.position) } == 3) return GameStatus.PLAYER1_WIN
            if(line.count { cell -> cell == Cell.PLAYER2(cell.position) } == 3) return GameStatus.PLAYER2_WIN
        }

        board.getBoard().forEach {
            if (it.value == Cell.NONE(it.key)) {
                return GameStatus.PLAYING
            }
        }

        return GameStatus.DRAW_GAME
    }
}
