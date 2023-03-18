package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.GameMode
import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.TurnResult

class GameManager(
    private var ticktacktoe: Ticktacktoe = AiTicktacktoe(RandomStrategy())
) {

    fun changeMode(mode: GameMode) {
        ticktacktoe = when (mode) {
            GameMode.RANDOM_AI_MODE -> {
                AiTicktacktoe(RandomStrategy())
            }
            GameMode.DRAW_AI_MODE -> {
                AiTicktacktoe(DrawStrategy())
            }
            GameMode.PlAYER_MODE -> {
                DefaultTicktacktoe()
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
