package com.example.tictectoe_domain

class TictectoeBoard {
    private val _tictectoeBoard = MutableList(10) { Cell.NONE }
    val tictectoeBoard: List<Cell>
        get() = _tictectoeBoard

    var cell = Cell.PLAYER1
        private set

    fun selectBoard(position: Int, playerTurn: PlayerTurn) {
        if (playerTurn == PlayerTurn.TURN_PLAYER1) {
            _tictectoeBoard[position] = Cell.PLAYER1
            cell = Cell.PLAYER2
        } else if (playerTurn == PlayerTurn.TURN_PLAYER2) {
            _tictectoeBoard[position] = Cell.PLAYER2
            cell = Cell.PLAYER1
        }
    }

    fun boardClear() {
        _tictectoeBoard.forEachIndexed { index, _ -> _tictectoeBoard[index] = Cell.NONE }

    }

    fun canSelect(position: Int) = _tictectoeBoard[position] == Cell.NONE
}
