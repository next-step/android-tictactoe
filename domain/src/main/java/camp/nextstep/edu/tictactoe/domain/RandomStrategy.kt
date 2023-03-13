package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Board
import camp.nextstep.edu.tictactoe.domain.model.Position

internal class RandomStrategy : AiStrategy {

    override fun getAiPosition(board: Board): Position {
        return board.getRandomCell().position
    }

}
