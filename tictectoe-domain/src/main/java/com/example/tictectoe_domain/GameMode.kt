package com.example.tictectoe_domain

enum class GameMode : SelectBoard {
    TWO_PLAYER {
        override fun selectBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
            return when (playerTurn) {
                PlayerTurn.TURN_PLAYER1 -> {
                    board.mark(Cell.PLAYER1(position))
                }
                PlayerTurn.TURN_PLAYER2 -> {
                    board.mark(Cell.PLAYER2(position))
                }
            }
        }
    },
    RANDOM {
        override fun selectBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
            var newBoard = board.mark(Cell.PLAYER1(position))
            if(newBoard.isFull()) {
                return newBoard
            }

            val nonSelectPosition = newBoard.getNonSelectPositionList()
            val selectPosition = nonSelectPosition.shuffled().first()

            newBoard = newBoard.mark(Cell.PLAYER2(selectPosition))
            return newBoard
        }
    },
    INTERMEDIATE_LEVEL {
        override fun selectBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board {
//            board.selectBoard(position, playerTurn)
            return board
        }
    };

}

interface SelectBoard {
    fun selectBoard(board: Board, position: Position, playerTurn: PlayerTurn): Board
}
