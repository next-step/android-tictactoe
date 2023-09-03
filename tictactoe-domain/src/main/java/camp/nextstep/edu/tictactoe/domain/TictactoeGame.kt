package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.strategy.IntermediateStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.Mode
import camp.nextstep.edu.tictactoe.domain.strategy.RandomStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TictactoeStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TwoPlayersStrategy

class TictactoeGame(
    private var tictactoeStrategy: TictactoeStrategy = IntermediateStrategy()
) {
    var tictactoeMap = TictactoeMap()
        private set
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

    fun gameReset() {
        tictactoeMap = TictactoeMap()
        isXTurn = true
    }

    fun markByStrategy(): TictactoeStatus? {
        val position = tictactoeStrategy.getNextTurnPosition(tictactoeMap) ?: return null
        return mark(position)
    }

    fun setMode(mode: Mode) {
        tictactoeStrategy = when (mode) {
            Mode.TWO_PLAYERS -> TwoPlayersStrategy()
            Mode.RANDOM -> RandomStrategy()
            Mode.INTERMEDIATE -> IntermediateStrategy()
        }
    }
}
