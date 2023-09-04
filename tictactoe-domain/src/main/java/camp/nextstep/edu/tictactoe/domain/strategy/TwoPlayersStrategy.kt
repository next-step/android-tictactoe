package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner

internal class TwoPlayersStrategy : TictactoeStrategy {
    override fun getNextTurnPosition(positions: Map<CellPosition, Owner>): CellPosition? {
        return null
    }
}
