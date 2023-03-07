package com.example.domain

internal class GameImpl constructor(
    initialTurn: Turn,
    initialBoardState: BoardState
) : Game {
    private var turn: Turn
    private var board: Board

    private var nowStatus: GameStatus
    private var _gameMode: GameMode
    internal val gameMode: GameMode
        get() = _gameMode


    private var _state: GameState
    override val state: GameState
        get() = _state

    init {
        this.turn = initialTurn
        this.board = Board(initialBoardState)

        this.nowStatus = checkStatus()
        this._gameMode = DrawMode()

        _state = GameState(nowStatus, this.turn, this.board.state)
    }

    internal constructor(
        turn: Turn,
        board: BoardState,
        gameMode: GameMode
    ) : this(turn, board) {
        this.turn = turn
        this.board = Board(board)
        this.nowStatus = checkStatus()
        this._gameMode = gameMode
        _state = GameState(nowStatus, this.turn, this.board.state)
    }

    override fun changeMode(mode: SelectMode) {
        val gameMode: GameMode = when (mode) {
            SelectMode.TwoPlayer -> TwoPlayerMode
            SelectMode.Random -> RandomMode()
            SelectMode.Draw -> DrawMode()
        }

        require(this._gameMode != gameMode) {
            "같은 모드로 변경하실 수 없습니다."
        }

        this._gameMode = gameMode
        reset()
    }

    internal fun changeRandomMode(algorithm: AssignAlgorithm) {
        require(this._gameMode !is RandomMode) {
            "같은 모드로 변경하실 수 없습니다."
        }
        this._gameMode = RandomMode(algorithm)
        reset()
    }


    override fun reset() {
        board = Board()
        turn = Turn()
        nowStatus = GameStatus.ONGOING
        _state = GameState(nowStatus, this.turn, board.state)
    }

    override fun assignBlock(blockIndex: Int) {
        require(nowStatus == GameStatus.ONGOING) {
            "게임이 종료되었습니다."
        }

        board.assignBlock(turn, blockIndex)
        nowStatus = checkStatus()
        _state = makeState()

        if (nowStatus == GameStatus.ONGOING) {
            assignBlockByAlgorithm()
        }
    }

    private fun assignBlockByAlgorithm() {
        when (_gameMode) {
            is RandomMode -> assignBlockByRandomAlgorithm()
            is DrawMode -> assignBlockByDrawAlgorithm()
            else -> {}
        }
    }

    private fun assignBlockByRandomAlgorithm() {
        board.assignBlock(turn, (_gameMode as RandomMode).calculateNextDoing(board.state))
        nowStatus = checkStatus()
        _state = makeState()
    }

    private fun assignBlockByDrawAlgorithm() {
        board.assignBlock(turn, (_gameMode as DrawMode).calculateNextDoing(board.state))
        nowStatus = checkStatus()
        _state = makeState()
    }

    private fun makeState(): GameState {
        return when (nowStatus) {
            GameStatus.ONGOING -> {
                turn = turn.next()
                GameState(GameStatus.ONGOING, turn, board.state)
            }
            GameStatus.X_WON -> {
                GameState(GameStatus.X_WON, turn, board.state)
            }
            GameStatus.O_WON -> {
                GameState(GameStatus.O_WON, turn, board.state)
            }
            GameStatus.DRAW -> {
                GameState(GameStatus.DRAW, turn, board.state)
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

        other as GameImpl

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

    companion object {
        internal fun createEmptyGame(): GameImpl {
            return GameImpl(Turn(), BoardState())
        }
    }
}
