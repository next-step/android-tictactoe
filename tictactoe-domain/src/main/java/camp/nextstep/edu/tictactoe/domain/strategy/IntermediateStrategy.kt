package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeMap
import camp.nextstep.edu.tictactoe.domain.WinnerChecker

internal class IntermediateStrategy : RandomStrategy() {
    override fun getNextTurnPosition(tictactoeMap: TictactoeMap): CellPosition {
        val position = WinnerChecker.getPlayerWinPosition(tictactoeMap.positions, Owner.X)
        if (position != null) {
            return position
        }

        return super.getNextTurnPosition(tictactoeMap)
    }
}
