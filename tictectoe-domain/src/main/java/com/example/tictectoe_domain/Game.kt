package com.example.tictectoe_domain

import kotlin.random.Random

class Game(
    private val board: TictectoeBoard = TictectoeBoard(),
    private val rule: TictectoeRule = TictectoeRule()
) {
    // 게임 상태
    private var _gameStatus = GameStatus.PLAYING
    val gameStatus: GameStatus
        get() = _gameStatus

    private var _playerTurn = PlayerTurn.TURN_PLAYER1
    val playerTurn: PlayerTurn
        get() = _playerTurn

    // 게임 모드
    private var _gameMode = GameMode.RANDOM
    val gameMode: GameMode
        get() = _gameMode
    fun changeTwoPlayerMode() {
        _gameMode = GameMode.TWO_PLAYER
    }
    fun changeRandomMode() {
        _gameMode = GameMode.RANDOM
    }
    fun getBoard() = board.tictectoeBoard

    fun gameReset() {
        board.boardClear()
        _gameStatus = GameStatus.PLAYING
        _playerTurn = PlayerTurn.TURN_PLAYER1
    }

    fun selectBoard(position: Int, nextPosition: Int? = null) {
        board.selectBoard(position, playerTurn)
        changeTurn()
        checkWinAndDraw()

        if(_gameMode == GameMode.TWO_PLAYER) return

        if(_playerTurn == PlayerTurn.TURN_PLAYER2
            && _gameStatus == GameStatus.PLAYING) {
            autoSelectBoard(nextPosition)
        }
    }

    fun isPlay() = gameStatus == GameStatus.PLAYING

    private fun autoSelectBoard(nextPosition: Int?) {
        val position: Int
        if(nextPosition == null) {
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
            val selectIndex = Random(System.nanoTime()).nextInt(playerNoneIndexList.size)
            position = playerNoneIndexList[selectIndex]
        } else {
            position = nextPosition
        }
        selectBoard(position)
    }

    fun canSelect(position: Int): Boolean {
        return board.canSelect(position)
    }

    // 플레이어 턴 변경
    private fun changeTurn() {
        _playerTurn = if(_playerTurn == PlayerTurn.TURN_PLAYER1) {
            PlayerTurn.TURN_PLAYER2
        } else {
            PlayerTurn.TURN_PLAYER1
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
