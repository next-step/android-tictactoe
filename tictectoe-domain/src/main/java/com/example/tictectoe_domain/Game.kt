package com.example.tictectoe_domain

class Game(
    private val board: TictectoeBoard = TictectoeBoard(),
    private val rule: TictectoeRule = TictectoeRule()
) {
    // 게임 상태
    private var _gameStatus = GameStatus.PLAYING
    val gameStatus: GameStatus
        get() = _gameStatus

    private var _playerTurn = PlayerTurn.TURN_PLAYER1
    private val playerTurn: PlayerTurn
        get() = _playerTurn

    // 게임 모드
    private var _gameMode = GameMode.RANDOM
    val gameMode: GameMode
        get() = _gameMode

    fun changeGameMode(gameMode: GameMode) {
        _gameMode = gameMode
    }

    fun getBoard() = board.tictectoeBoard

    fun gameReset() {
        board.boardClear()
        _gameStatus = GameStatus.PLAYING
        _playerTurn = PlayerTurn.TURN_PLAYER1
    }

    fun selectBoard(position: Int) {
        _gameMode.selectBoard(board, position, playerTurn)
        if(_gameMode == GameMode.TWO_PLAYER) {
            changeTurn()
        }
        checkWinAndDraw()
    }

    fun isPlay() = gameStatus == GameStatus.PLAYING

    fun canSelect(position: Int): Boolean {
        return board.canSelect(position)
    }

    // 플레이어 턴 변경
    private fun changeTurn() {
        _playerTurn = when(_playerTurn) {
            PlayerTurn.TURN_PLAYER1 -> PlayerTurn.TURN_PLAYER2
            PlayerTurn.TURN_PLAYER2 -> PlayerTurn.TURN_PLAYER1
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
