package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.State
import camp.nextstep.edu.tictactoe.domain.model.Turn
import camp.nextstep.edu.tictactoe.domain.model.TurnResult

internal class AiTicktacktoe(private val strategy: AiStrategy) : Ticktacktoe(Turn.X) {
    private fun getAiPosition(): Position {
        return strategy.getAiPosition(board)
    }

    override fun runOneTurn(position: Position): TurnResult {
        val userGameResult = super.put(position)
        switchTurn()

        if (userGameResult.first == State.InProgress) {
            val aiPoint = getAiPosition()
            val aiGameResult = super.put(aiPoint)
            switchTurn()
            return TurnResult(
                aiGameResult.first, aiGameResult.second
            )

        }
        return TurnResult(
            userGameResult.first,
            userGameResult.second
        )
    }


}
