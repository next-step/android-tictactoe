package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.GameMode
import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.TurnResult

class GameManager(
    private var ticktacktoe: Ticktacktoe = AiTicktacktoe()
) {

    fun changeMode(mode: GameMode) {
        ticktacktoe = when (mode) {
            GameMode.AI_MODE -> {
                AiTicktacktoe()
            }
            else -> {
                DefaultTicktacktoe()
            }
        }
    }

    fun isFinish(): Boolean {
        return ticktacktoe.isFinish
    }

    fun reset() {
        ticktacktoe.reset()
    }

    fun runOneTurn(position: Position): TurnResult {
        return ticktacktoe.runOneTurn(position)
    }

    fun isNotValidData(position: Position): Boolean {
        return ticktacktoe.isNotValidData(position)
    }


}