package com.nextstep.edu.tictactoe.domain

import kotlin.math.sqrt

class Tictactoe {
    var isXTurn: Cell = Cell.NONE
        private set

    fun toggleTurn(): Cell {
        isXTurn = when (isXTurn) {
            Cell.X -> {
                Cell.O
            }
            Cell.O -> {
                Cell.X
            }
            else -> {
                Cell.X
            }
        }
        return isXTurn
    }

    fun restart() {
        isXTurn = Cell.NONE
    }

    fun findWinner(board: List<Cell?>): Winner {
        return if (findWinnerForRow(board) == Cell.X
            || findWinnerForColumn(board) == Cell.X
            || findWinnerForLeftToRightDiagonal(board) == Cell.X
            || findWinnerForRightToLeftDiagonal(board) == Cell.X
        ) {
            Winner.X
        } else if (findWinnerForRow(board) == Cell.O
            || findWinnerForColumn(board) == Cell.O
            || findWinnerForLeftToRightDiagonal(board) == Cell.O
            || findWinnerForRightToLeftDiagonal(board) == Cell.O
        ) {
            Winner.O
        } else if (isDraw(board)) {
            Winner.DRAW
        } else {
            Winner.NONE
        }
    }

    fun findWinnerForRow(board: List<Cell?>): Cell {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter: Int
        var oCounter: Int

        for (i in board.indices step cornerSize) {
            xCounter = 0
            oCounter = 0

            repeat(cornerSize) {
                if (board[i + it] == Cell.X) {
                    xCounter++
                }
                if (board[i + it] == Cell.O) {
                    oCounter++
                }
            }

            if (xCounter == cornerSize) {
                return Cell.X
            }
            if (oCounter == cornerSize) {
                return Cell.O
            }
        }

        return Cell.NONE
    }

    fun findWinnerForColumn(board: List<Cell?>): Cell {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter: Int
        var oCounter: Int

        repeat(cornerSize) {
            xCounter = 0
            oCounter = 0

            for (i in board.indices step cornerSize) {
                if (board[i + it] == Cell.X) {
                    xCounter++
                }
                if (board[i + it] == Cell.O) {
                    oCounter++
                }
            }

            if (xCounter == cornerSize) {
                return Cell.X
            }
            if (oCounter == cornerSize) {
                return Cell.O
            }
        }

        return Cell.NONE
    }

    fun findWinnerForLeftToRightDiagonal(board: List<Cell?>): Cell {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter = 0
        var oCounter = 0

        for (i in 0 until cornerSize) {
            if (board[i * (cornerSize + 1)] == Cell.X) {
                xCounter++
            }
            if (board[i * (cornerSize + 1)] == Cell.O) {
                oCounter++
            }
        }

        if (xCounter == cornerSize) {
            return Cell.X
        }
        if (oCounter == cornerSize) {
            return Cell.O
        }

        return Cell.NONE
    }

    fun findWinnerForRightToLeftDiagonal(board: List<Cell?>): Cell {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter = 0
        var oCounter = 0

        for (i in 1..cornerSize) {
            if (board[i * (cornerSize - 1)] == Cell.X) {
                xCounter++
            }
            if (board[i * (cornerSize - 1)] == Cell.O) {
                oCounter++
            }
        }

        if (xCounter == cornerSize) {
            return Cell.X
        }
        if (oCounter == cornerSize) {
            return Cell.O
        }

        return Cell.NONE
    }

    fun isDraw(board: List<Cell?>): Boolean {
        return board.none { it == Cell.NONE }
    }
}
