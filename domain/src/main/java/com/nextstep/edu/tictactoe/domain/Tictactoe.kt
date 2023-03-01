package com.nextstep.edu.tictactoe.domain

class Tictactoe {
    fun toggleTurn(): Cell {
        return TurnToggle.toggleTurn()
    }

    fun restart() {
        TurnToggle.restart()
    }

    fun findWinner(board: List<Cell?>): Winner {
        return WinnerSelector.findWinner(board)
    }
}
