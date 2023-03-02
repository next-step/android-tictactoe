package com.nextstep.edu.tictactoe.domain

object TurnToggle {
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
}
