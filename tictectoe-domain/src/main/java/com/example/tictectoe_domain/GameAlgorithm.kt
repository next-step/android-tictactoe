package com.example.tictectoe_domain

sealed class GameAlgorithm : GamePlay

internal object TwoPlayer : GameAlgorithm() {
    override fun markBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
        return when (playerTurn) {
            PlayerTurn.TURN_PLAYER1 -> {
                board.mark(Cell.PLAYER1(position))
            }

            PlayerTurn.TURN_PLAYER2 -> {
                board.mark(Cell.PLAYER2(position))
            }
        }
    }

}

internal object Random : GameAlgorithm() {
    override fun markBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
        val newBoard = board.mark(Cell.PLAYER1(position))
        if (newBoard.isFull()) {
            return newBoard
        }
        return randomMark(newBoard)
    }
}

internal object IntermediateLevel : GameAlgorithm() {
    override fun markBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
        val newBoard = board.mark(Cell.PLAYER1(position))

        if (newBoard.isFull()) {
            return newBoard
        }

        // 1. 라인에 2개의 Player2와 하나의 None이 있는 곳을 mark한다.
        winingMark(newBoard)?.let { return it }

        // 2. 1번 조건에 만족하는 곳이 없다면 라인에 2개의 Player1와 하나의 None이 있는 곳을 mark한다.
        defenseMark(newBoard)?.let { return it }

        // 3. 1번, 2번 조건에 만족하는 곳이 없다면 랜덤하게 mark한다.
        return randomMark(newBoard)
    }
}

private fun randomMark(board: Board): Board {
    val nonSelectPosition = board.getNonSelectPositionList()
    val selectPosition = nonSelectPosition.shuffled().first()

    return board.mark(Cell.PLAYER2(selectPosition))
}

private fun winingMark(board: Board): Board? {
    board.lines.forEach { line ->
        // Player2의 mark 2개인 경우
        if (line.count { it is Cell.PLAYER2 } == 2 && line.count { it is Cell.NONE } == 1) {
            line.forEach { cell ->
                if (cell is Cell.NONE) {
                    return board.mark(Cell.PLAYER2(cell.position))
                }
            }
        }
    }
    return null
}

private fun defenseMark(board: Board): Board? {
    board.lines.forEach { line ->
        // Player2의 mark 1개인 경우
        if (line.count { it is Cell.PLAYER1 } == 2 && line.count { it is Cell.NONE } == 1) {
            line.forEach { cell ->
                if (cell is Cell.NONE) {
                    return board.mark(Cell.PLAYER2(cell.position))
                }
            }
        }
    }
    return null
}

interface GamePlay {
    fun markBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board
}
