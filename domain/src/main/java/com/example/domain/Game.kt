package com.example.domain

class Game(
    turn: Int = Turn.INIT_TURN,
    board: Board = Board.createEmptyBoard(),
    gameMode: GameMode = RandomMode()
) {

    private var turn: Turn
    private var board: Board
    private var nowStatus: GameStatus
    private var gameMode: GameMode
    private var _state: GameState
    val state: GameState
        get() = _state

    init {
        this.turn = Turn(turn)
        this.board = Board(board.state.blocks)
        this.nowStatus = checkStatus()
        this.gameMode = gameMode
        _state = GameState(nowStatus, this.turn, board.state, this.gameMode)
    }

    fun changeMode(gameMode: GameMode) {
        require(gameMode != this.gameMode) {
            "같은 모드로 변경하실 수 없습니다."
        }
        this.gameMode = gameMode
        reset()
    }

    fun reset() {
        board = Board.createEmptyBoard()
        turn = Turn()
        nowStatus = GameStatus.ONGOING
        _state = GameState(nowStatus, this.turn, board.state, gameMode)
    }

    fun assignBlock(blockIndex: Int) {
        require(nowStatus == GameStatus.ONGOING) {
            "게임이 종료되었습니다."
        }

        board.assignBlock(turn, blockIndex)
        nowStatus = checkStatus()
        _state = makeState()

        if (nowStatus == GameStatus.ONGOING && gameMode is RandomMode) {
            assignBlockByRandom()
        }

    }

    private fun assignBlockByRandom() {
        board.assignBlock(turn, (gameMode as RandomMode).calculateNextDoing(board.state))
        nowStatus = checkStatus()
        _state = makeState()
    }

    private fun makeState(): GameState {
        return when (nowStatus) {
            GameStatus.ONGOING -> {
                turn = turn.next()
                GameState(GameStatus.ONGOING, turn, board.state, gameMode)
            }
            GameStatus.X_WON -> {
                GameState(GameStatus.X_WON, turn, board.state, gameMode)
            }
            GameStatus.O_WON -> {
                GameState(GameStatus.O_WON, turn, board.state, gameMode)
            }
            GameStatus.DRAW -> {
                GameState(GameStatus.DRAW, turn, board.state, gameMode)
            }
        }
    }

    private fun checkStatus(): GameStatus {
        return if (turn.whoseTurn() == Player.X && board.hasOneLine(turn)) {
            GameStatus.X_WON
        } else if (turn.whoseTurn() == Player.O && board.hasOneLine(turn)) {
            GameStatus.O_WON
        } else if (board.isFull()) {
            GameStatus.DRAW
        } else {
            GameStatus.ONGOING
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Game

        if (turn != other.turn) return false
        if (board != other.board) return false
        if (nowStatus != other.nowStatus) return false
        if (_state != other._state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = turn.hashCode()
        result = 31 * result + board.hashCode()
        result = 31 * result + nowStatus.hashCode()
        result = 31 * result + _state.hashCode()
        return result
    }
}
