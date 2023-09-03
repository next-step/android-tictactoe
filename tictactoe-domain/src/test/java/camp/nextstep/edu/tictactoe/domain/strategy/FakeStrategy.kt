package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

class FakeStrategy(private val positions: Map<CellPosition, Owner>) : TictactoeStrategy {

    override fun getNextTurnPosition(tictactoeMap: TictactoeMap): CellPosition {
        val emptyPositions = positions.filter { (_, owner) ->
            owner == Owner.NONE
        }
        return CellPosition.values()
            .filter {
                emptyPositions.contains(it)
            }[0]
    }
}
