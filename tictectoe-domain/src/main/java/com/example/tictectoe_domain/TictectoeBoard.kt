package com.example.tictectoe_domain

class TictectoeBoard {
    private val _tictectoeBoard = MutableList(10){ Cell.NONE}
    val tictectoeBoard: List<Cell>
        get() = _tictectoeBoard

    var cell = Cell.PLAYER1
        private set

    fun selectBoard(position: Int, gameStatus: GameStatus) {
        if(gameStatus == GameStatus.TURN_PLAYER1) {
            _tictectoeBoard[position] = Cell.PLAYER1
            cell = Cell.PLAYER2
        } else if(gameStatus == GameStatus.TURN_PLAYER2) {
            _tictectoeBoard[position] = Cell.PLAYER2
            cell = Cell.PLAYER1
        }
    }

    fun canSelect(position: Int) = _tictectoeBoard[position] == Cell.NONE
}
