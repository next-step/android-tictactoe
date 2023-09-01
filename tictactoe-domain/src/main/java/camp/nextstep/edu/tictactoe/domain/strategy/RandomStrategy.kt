package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

class RandomStrategy : TictactoeStrategy {
    override fun getNextTurnPosition(tictactoeMap: TictactoeMap): CellPosition {
        val emptyPositions = tictactoeMap.positions.filter { (_, owner) ->
            owner == Owner.NONE
        }
        return CellPosition.values()
            .filter {
                emptyPositions.contains(it)
            }
            .random()
    }
}
