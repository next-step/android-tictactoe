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
            GameMode.PlAYER_MODE -> {
                DefaultTicktacktoe()
            }
            GameMode.DRAW_AI_MODE -> {
                DrawAiTicktacktoe()
            }
        }
    }

    fun reset() {
        ticktacktoe.reset()
    }

    fun runOneTurn(position: Position): TurnResult {
        return ticktacktoe.runOneTurn(position)
    }


}
