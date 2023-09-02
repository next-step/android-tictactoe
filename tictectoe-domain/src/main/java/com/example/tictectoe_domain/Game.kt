package com.example.tictectoe_domain

import java.util.Random

class Game(
    private var board: TictectoeBoard = TictectoeBoard(),
    private val rule: TictectoeRule = TictectoeRule()
) {
    // 게임 상태
    private var _gameStatus = GameStatus.TURN_PLAYER1
    val gameStatus: GameStatus
        get() = _gameStatus

    // 게임 모드
    private var gameMode = GameMode.RANDOM

    fun changeTwoPlayerMode() {
        gameMode = GameMode.TWO_PLAYER
    }
    fun changeRandomMode() {
        gameMode = GameMode.RANDOM
    }
    fun getBoard() = board.tictectoeBoard

    fun gameReset() {
        board = TictectoeBoard()
        _gameStatus = GameStatus.TURN_PLAYER1
    }

    fun selectBoard(position: Int) {
        board.selectBoard(position, gameStatus)
        changeTurn()
        checkWinAndDraw()

        if(gameMode == GameMode.TWO_PLAYER) return

        if(_gameStatus == GameStatus.TURN_PLAYER2) {
            autoSelectBoard()
        }
    }

    fun isPlay() = when(_gameStatus) {
        GameStatus.TURN_PLAYER1, GameStatus.TURN_PLAYER2 -> true
        else -> false
    }

    private fun autoSelectBoard() {
        // 빈칸을 찾는다.
        val playerNoneIndexList = mutableListOf<Int>()
        // 빈칸 리스트에 추가
        for((index, player) in board.tictectoeBoard.withIndex()) {
            if(player == Cell.NONE) {
                playerNoneIndexList.add(index)
            }
        }
        // 빈칸 중 0은 제외 한다.
        playerNoneIndexList.removeAt(0)
        // 랜덤으로 선택
        val selectIndex = Random().nextInt(playerNoneIndexList.size)

        selectBoard(playerNoneIndexList[selectIndex])
    }

    fun canSelect(position: Int): Boolean {
        return board.canSelect(position)
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
