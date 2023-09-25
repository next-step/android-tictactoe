package com.example.tictectoe_domain

import javax.inject.Inject

class Game @Inject constructor(
    private var board: Board,
    private val rule: TictectoeRule
) {
    // 게임 상태
    private var _gameStatus = GameStatus.PLAYING
    val gameStatus: GameStatus
        get() = _gameStatus

    private var _playerTurn = PlayerTurn.TURN_PLAYER1
    val playerTurn: PlayerTurn
        get() = _playerTurn

    private var _gameAlgorithm: GameAlgorithm = Random
    val gameAlgorithm: GameAlgorithm
        get() = _gameAlgorithm
    fun changeGameMode(gameMode: GameMode) {
        _gameAlgorithm = when(gameMode) {
            GameMode.TWO_PLAYER_MODE -> TwoPlayer
            GameMode.RANDOM_MODE -> Random
            GameMode.INTERMEDIATE_LEVEL_MODE -> IntermediateLevel
        }
    }

    fun getBoard() = board

    fun gameReset() {
        board = Board.EMPTY
        _gameStatus = GameStatus.PLAYING
        _playerTurn = PlayerTurn.TURN_PLAYER1
    }

    fun selectBoard(position: Position, testGameAlgorithm: GameAlgorithm? = null) {
        testGameAlgorithm?.let { _gameAlgorithm = testGameAlgorithm }

        if (!board.canMark(position)) return
        board = _gameAlgorithm.markBoard(board, position, playerTurn)

        if (_gameAlgorithm == TwoPlayer) {
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
