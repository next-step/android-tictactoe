package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.strategy.DefaultStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.RandomStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TictactoeStrategy

class TictactoeGame(
    private val tictactoeStrategy: TictactoeStrategy = DefaultStrategy()
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
        if (isXTurn) return null
        return when (tictactoeStrategy) {
            is RandomStrategy -> {
                mark(tictactoeStrategy.getPosition(tictactoeMap))
            }

            else -> null
        }
    }
}
