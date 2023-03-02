package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.*
import camp.nextstep.edu.tictactoe.domain.model.Map

abstract class Ticktacktoe constructor(
    _currentTurn: Turn = Turn.X
) {
    var map: Map = Map.EMPTY
        private set
    private var state: State = State.InProgress
    var currentTurn = _currentTurn
        private set

    fun put(position: Position): Pair<State, Cell> {

        val cell = when (currentTurn) {
            Turn.X -> Cell.X(position)
            Turn.O -> Cell.O(position)
        }
        map = map.mark(cell)
        state = checkState()

        isFinish = state != State.InProgress
        return Pair(state, cell)
    }

    private fun checkState(): State = when {
        map.lines.isX() -> State.WinX
        map.lines.isO() -> State.WinO
        map.lines.isDraw() -> State.Draw
        else -> State.InProgress
    }

    var isFinish: Boolean = false
        private set

    fun isNotValidData(position: Position): Boolean {
        return (map.isNotValidData(position) || isFinish)
    }

    abstract fun runOneTurn(position: Position): TurnResult

    fun reset() {
        isFinish = false
        currentTurn = Turn.X
        map = Map.EMPTY
    }

    fun switchTurn() {
        currentTurn = currentTurn.switch()
    }

    companion object {
        const val MAP_SIZE = 3
    }


}

