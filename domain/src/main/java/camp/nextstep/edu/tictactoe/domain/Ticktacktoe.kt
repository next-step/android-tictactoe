package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.*

abstract class Ticktacktoe constructor(
    _currentTurn: Turn = Turn.X
) {
    var board: Board = Board.EMPTY
        private set

    private var state: State = State.InProgress

    var currentTurn = _currentTurn
        private set

    var isFinish: Boolean = false
        private set

    fun put(position: Position): Pair<State, Board> {
        checkLegalMove(position)
        val cell = when (currentTurn) {
            Turn.X -> Cell.X(position)
            Turn.O -> Cell.O(position)
        }
        board = board.mark(cell)
        state = checkState()

        isFinish = state != State.InProgress
        return Pair(state, board)
    }

    private fun checkState(): State = when {
        board.lines.isX() -> State.WinX
        board.lines.isO() -> State.WinO
        board.lines.isDraw() -> State.Draw
        else -> State.InProgress
    }

    private fun checkLegalMove(position: Position) {
        if (board.isDuplicatedInput(position))
            throw TurnError.DuplicatedInput("이미 선택된 좌표 입니다.")
        if (isFinish)
            throw TurnError.GameFinish("게임이 종료 되었습니다.")

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

}

