package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.*
import camp.nextstep.edu.tictactoe.domain.model.Board

abstract class Ticktacktoe constructor(
    _currentTurn: Turn = Turn.X
) {
    var board: Board = Board.EMPTY
        private set
    private var state: State = State.InProgress
    var currentTurn = _currentTurn
        private set

    fun put(position: Position): Pair<State, Cell> {

        val cell = when (currentTurn) {
            Turn.X -> Cell.X(position)
            Turn.O -> Cell.O(position)
        }
        board = board.mark(cell)
        state = checkState()

        isFinish = state != State.InProgress
        return Pair(state, cell)
    }

    private fun checkState(): State = when {
        board.lines.isX() -> State.WinX
        board.lines.isO() -> State.WinO
        board.lines.isDraw() -> State.Draw
        else -> State.InProgress
    }

    var isFinish: Boolean = false
        private set

    fun isNotValidData(position: Position): Boolean {
        return (board.isNotValidData(position) || isFinish)
    }

    abstract fun runOneTurn(position: Position): TurnResult

    fun reset() {
        isFinish = false
        currentTurn = Turn.X
        board = Board.EMPTY
    }

    fun switchTurn() {
        currentTurn = currentTurn.switch()
    }

    companion object {
        const val BOARD_SIZE = 3
    }


}

