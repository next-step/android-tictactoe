package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.strategy.TictactoeStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TwoPlayersStrategy

class TictactoeGame(
    private val tictactoeStrategy: TictactoeStrategy = TwoPlayersStrategy()
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

    fun continueGame(): TictactoeStatus? {
        val position = tictactoeStrategy.getPosition(tictactoeMap) ?: return null
        return mark(position)
    }
}
