package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

class FakeStrategy : TictactoeStrategy {

    override fun getNextTurnPosition(tictactoeMap: TictactoeMap): CellPosition {
        return CellPosition.values()
            .random()
    }
}
