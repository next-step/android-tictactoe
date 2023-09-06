package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner

interface TictactoeStrategy {

    fun getNextTurnPosition(positions: Map<CellPosition, Owner>): CellPosition?
}
