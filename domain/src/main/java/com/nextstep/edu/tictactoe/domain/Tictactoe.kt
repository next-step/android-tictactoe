package com.nextstep.edu.tictactoe.domain

import kotlin.math.sqrt

class Tictactoe {
    private var isXTurn: Boolean? = null

    fun isXTurn(): Boolean? {
        isXTurn = if (isXTurn == null) {
            true
        } else {
            isXTurn != true
        }
        return isXTurn
    }

    fun restart() {
        isXTurn = null
    }

    fun findWinner(board: List<Boolean?>): Winner {
        return if (isWinnerForRow(board) == true
            || isWinnerForLeftToRightDiagonal(board) == true
            || isWinnerForRightToLeftDiagonal(board) == true
        ) {
            Winner.X
        } else if (isWinnerForRow(board) == false
            || isWinnerForLeftToRightDiagonal(board) == false
            || isWinnerForRightToLeftDiagonal(board) == false
        ) {
            Winner.O
        } else if (isDraw(board)) {
            Winner.DRAW
        } else {
            Winner.NONE
        }
    }

    fun isWinnerForRow(board: List<Boolean?>): Boolean? {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter: Int
        var oCounter: Int

        for (i in board.indices step 3) {
            xCounter = 0
            oCounter = 0

            repeat(cornerSize) {
                if (board[i + it] == true) {
                    xCounter++
                }
                if (board[i + it] == false) {
                    oCounter++
                }
            }

            if (xCounter == cornerSize) {
                return true
            }
            if (oCounter == cornerSize) {
                return false
            }
        }

        return null
    }

    fun isWinnerForColumn(board: List<Boolean?>): Boolean? {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter: Int
        var oCounter: Int

        repeat(cornerSize) {
            xCounter = 0
            oCounter = 0

            for (i in board.indices step 3) {
                if (board[i + it] == true) {
                    xCounter++
                }
                if (board[i + it] == false) {
                    oCounter++
                }
            }

            if (xCounter == cornerSize) {
                return true
            }
            if (oCounter == cornerSize) {
                return false
            }
        }

        return null
    }

    fun isWinnerForLeftToRightDiagonal(board: List<Boolean?>): Boolean? {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter = 0
        var oCounter = 0

        for (i in 0 until cornerSize) {
            if (board[i * (cornerSize + 1)] == true) {
                xCounter++
            }
            if (board[i * (cornerSize + 1)] == false) {
                oCounter++
            }
        }

        if (xCounter == cornerSize) {
            return true
        }
        if (oCounter == cornerSize) {
            return false
        }

        return null
    }

    fun isWinnerForRightToLeftDiagonal(board: List<Boolean?>): Boolean? {
        val cornerSize = sqrt(board.size.toFloat()).toInt()
        var xCounter = 0
        var oCounter = 0

        for (i in 1..cornerSize) {
            if (board[i * (cornerSize - 1)] == true) {
                xCounter++
            }
            if (board[i * (cornerSize - 1)] == false) {
                oCounter++
            }
        }

        if (xCounter == cornerSize) {
            return true
        }
        if (oCounter == cornerSize) {
            return false
        }

        return null
    }

    fun isDraw(board: List<Boolean?>): Boolean {
        return board.none { it == null }
    }
}
