package com.example.tictectoe_domain

class Game(
    private var board: Board = Board.EMPTY,
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
    private var _gameMode: GameMode = GameMode.RANDOM_MODE
    val gameMode: GameMode
        get() = _gameMode

    fun changeGameMode(gameMode: GameMode) {
        _gameMode = gameMode
    }

    fun getBoard() = board

    fun gameReset() {
        board = Board.EMPTY
        _gameStatus = GameStatus.PLAYING
        _playerTurn = PlayerTurn.TURN_PLAYER1
    }

    fun selectBoard(position: Position, testGameMode: GameMode? = null) {
        testGameMode?.let { _gameMode = testGameMode }

        if (!board.canMark(position)) return

        board = when (_gameMode) {
            GameMode.TWO_PLAYER_MODE -> TwoPlayer.markBoard(board, position, playerTurn)
            GameMode.RANDOM_MODE -> Random.markBoard(board, position, playerTurn)
            GameMode.INTERMEDIATE_LEVEL_MODE -> IntermediateLevel.markBoard(board, position, playerTurn)
        }

        if (_gameMode == GameMode.TWO_PLAYER_MODE) {
            changeTurn()
        }
    }

    fun isPlay() = _gameStatus == GameStatus.PLAYING

    // 플레이어 턴 변경
    private fun changeTurn() {
        _playerTurn = when (_playerTurn) {
            PlayerTurn.TURN_PLAYER1 -> PlayerTurn.TURN_PLAYER2
            PlayerTurn.TURN_PLAYER2 -> PlayerTurn.TURN_PLAYER1
        }
    }

    // 승리 확인
    fun checkGameStatus(): GameStatus {
        _gameStatus = rule.checkGameStatus(board)
        return _gameStatus
    }
}
