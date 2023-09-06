package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import javax.inject.Inject

internal class TwoPlayersStrategy @Inject constructor() : TictactoeStrategy {
    override fun getNextTurnPosition(positions: Map<CellPosition, Owner>): CellPosition? {
        return null
    }
}
