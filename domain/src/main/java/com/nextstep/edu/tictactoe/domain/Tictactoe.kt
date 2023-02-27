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
        return if (isWinnerRow(board) == true) {
            Winner.X
        } else if (isWinnerRow(board) == false) {
            Winner.O
        } else {
            Winner.NONE
        }
    }

    fun isWinnerRow(board: List<Boolean?>): Boolean? {
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

    fun isWinnerColumn(board: List<Boolean?>): Boolean? {
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
}
