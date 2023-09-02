package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

internal class TwoPlayersStrategy : TictactoeStrategy {
    override fun getNextTurnPosition(tictactoeMap: TictactoeMap): CellPosition? {
        return null
    }
}
