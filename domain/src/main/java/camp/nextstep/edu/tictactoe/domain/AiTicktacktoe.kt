package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.State
import camp.nextstep.edu.tictactoe.domain.model.Turn
import camp.nextstep.edu.tictactoe.domain.model.TurnResult

class AiTicktacktoe : Ticktacktoe(Turn.X) {
    private fun putFromAI(): Position {
        return map.getRandomCell().position

    }

    override fun runOneTurn(position: Position): TurnResult {
        val userGameResult = super.put(position)
        switchTurn()

        if (userGameResult.first == State.InProgress) {
            val aiPoint = putFromAI()
            val aiGameResult = super.put(aiPoint)
            switchTurn()
            return TurnResult(
                aiGameResult.first, listOf(
                    userGameResult.second,
                    aiGameResult.second
                )
            )

        }
        return TurnResult(userGameResult.first, listOf(userGameResult.second))
    }


}