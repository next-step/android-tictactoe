package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.strategy.IntermediateStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.Mode
import camp.nextstep.edu.tictactoe.domain.strategy.RandomStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TictactoeStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TwoPlayersStrategy
import javax.inject.Inject

class TictactoeGame @Inject constructor(
    private var tictactoeStrategy: TictactoeStrategy,
    private var tictactoeMap: TictactoeMap
) {
    var isXTurn: Boolean = true
        private set

    fun mark(position: CellPosition): TictactoeStatus {
        tictactoeMap.set(position, isXTurn)
        changeTurn()
        return WinnerChecker.check(tictactoeMap.positions)
    }

    private fun changeTurn() {
        isXTurn = !isXTurn
    }

    fun setMode(mode: Mode) {
        tictactoeStrategy = when (mode) {
            Mode.TWO_PLAYERS -> TwoPlayersStrategy()
            Mode.RANDOM -> RandomStrategy()
            Mode.INTERMEDIATE -> IntermediateStrategy()
        }
        resetMap()
    }

    fun resetMap() {
        tictactoeMap.reset()
        isXTurn = true
    }

    fun markByStrategy(): TictactoeStatus? {
        val position = tictactoeStrategy.getNextTurnPosition(tictactoeMap.positions) ?: return null
        return mark(position)
    }

    fun getMapPositions(): Map<CellPosition, Owner> {
        return tictactoeMap.positions
    }
}
