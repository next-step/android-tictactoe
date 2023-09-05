package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.WinnerChecker
import javax.inject.Inject

internal class IntermediateStrategy @Inject constructor() : RandomStrategy() {
    override fun getNextTurnPosition(positions: Map<CellPosition, Owner>): CellPosition {
        return WinnerChecker.getPlayerWinPosition(positions, Owner.O)
            ?: WinnerChecker.getPlayerWinPosition(positions, Owner.X)
            ?: super.getNextTurnPosition(positions)
    }
}
