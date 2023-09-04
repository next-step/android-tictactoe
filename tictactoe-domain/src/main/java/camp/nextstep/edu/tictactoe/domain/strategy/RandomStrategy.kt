package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner

internal open class RandomStrategy : TictactoeStrategy {
    override fun getNextTurnPosition(positions: Map<CellPosition, Owner>): CellPosition {
        val emptyPositions = positions.filter { (_, owner) ->
            owner == Owner.NONE
        }
        return CellPosition.values()
            .filter {
                emptyPositions.contains(it)
            }
            .random()
    }
}
