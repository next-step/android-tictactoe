package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.Turn
import camp.nextstep.edu.tictactoe.domain.model.TurnResult

class DefaultTicktacktoe : Ticktacktoe(Turn.X) {
    override fun runOneTurn(position: Position): TurnResult {
        val result = super.put(position)
        switchTurn()
        return TurnResult(result.first, result.second)
    }

}
