package com.example.domain

class Game(turn: Int = 0, board: Board = Board.createEmptyBoard()) {

    private var turn: Turn
    private var board: Board
    private var nowStatus: GameStatus

    private var _state: GameState
    val state: GameState
        get() = _state

    init {
        this.turn = Turn(turn)
        this.board = Board(board.state.blocks)
        this.nowStatus = checkStatus()
        _state = GameState(nowStatus, this.turn, board.state)
    }

    fun assignBlock(blockIndex: Int) {
        require(nowStatus == GameStatus.ONGOING) {
            "게임이 종료되었습니다."
        }

        board.assignBlock(turn, blockIndex)
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

    fun reset() {
        board = Board.createEmptyBoard()
        turn = Turn(0)
        nowStatus = GameStatus.ONGOING
        _state = GameState(nowStatus, this.turn, board.state)
    }

    private fun checkStatus(): GameStatus {
        return if (board.isFull()) {
            GameStatus.DRAW
        } else if (turn.whoseTurn() == Player.X && board.hasOneLine(turn)) {
            GameStatus.X_WON
        } else if (turn.whoseTurn() == Player.O && board.hasOneLine(turn)) {
            GameStatus.O_WON
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
