package com.example.tictectoe_domain

import java.util.Random

class Game(
    private val board: TictectoeBoard = TictectoeBoard(),
    private val rule: TictectoeRule = TictectoeRule()
) {

    // 게임 상태
    private var _gameStatus = GameStatus.TURN_PLAYER1
    val gameStatus: GameStatus
        get() = _gameStatus

    fun getCell(): Cell {
        return board.cell
    }

    fun getBoard() = board.tictectoeBoard

    fun selectBoard(position: Int) {
        board.selectBoard(position, gameStatus)
        changeGameStatus()
    }

    fun isPlay() = when(_gameStatus) {
        GameStatus.TURN_PLAYER1, GameStatus.TURN_PLAYER2 -> true
        else -> false
    }

    fun canSelect(position: Int): Boolean {
        return board.canSelect(position)
    }

    private fun changeGameStatus() {
        changeTurn()
        checkWinAndDraw()
    }

    // 플레이어 턴 변경
    private fun changeTurn() {
        _gameStatus = if(_gameStatus == GameStatus.TURN_PLAYER1) {
            GameStatus.TURN_PLAYER2
        } else {
            GameStatus.TURN_PLAYER1
        }
    }

    // 승리 확인
    private fun checkWinAndDraw() {
        val winningPlayer = rule.getWinningPlayer(board.tictectoeBoard)
        if(winningPlayer == Cell.PLAYER1) {
            _gameStatus = GameStatus.PLAYER1_WIN
        } else if(winningPlayer == Cell.PLAYER2) {
            _gameStatus = GameStatus.PLAYER2_WIN
        } else if(rule.isDraw(board.tictectoeBoard)) {
            _gameStatus = GameStatus.DRAW_GAME
        }
    }
}
