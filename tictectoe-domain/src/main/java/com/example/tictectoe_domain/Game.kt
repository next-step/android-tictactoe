package com.example.tictectoe_domain

class Game(private val board: TictectoeBoard, private val rule: TictectoeRule) {

    fun startGame() {
        board.initBoard()
    }

    fun getPlayer(): Player {
        return board.getPlayer()
    }

    fun selectBoard(position: Int) {
        board.selectBoard(position)
        board.changePlayer()
    }

    fun canSelect(position: Int): Boolean {
        return board.canSelect(position)
    }

    fun checkGameWin(): Player {
        return rule.getWinningPlayer(board.getBoard())
    }

    fun checkGameDraw(): Boolean {
        return rule.isDraw(board.getBoard())
    }
}
