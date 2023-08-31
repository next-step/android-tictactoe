package com.example.tictectoe_domain

class TictectoeBoard {
    private lateinit var board: MutableList<Player>
    private var player = Player.PLAYER1

    init {
        initBoard()
    }

    fun initBoard() {
        board = MutableList(10){ Player.NONE}
        player = Player.PLAYER1
    }

    fun selectBoard(position: Int) {
        board[position] = player
    }

    fun canSelect(position: Int) = board[position] == Player.NONE

    fun getBoard() = board
    fun getPlayer() = player

    fun changePlayer() {
        player = if(player == Player.PLAYER1) {
            Player.PLAYER2
        } else {
            Player.PLAYER1
        }
    }
}
