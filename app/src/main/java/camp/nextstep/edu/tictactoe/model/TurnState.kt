package camp.nextstep.edu.tictactoe.model

import camp.nextstep.edu.tictactoe.domain.model.Cell

enum class TurnState {
    X,
    O,
    EMPTY;

    companion object {
        fun from(cell: Cell): TurnState =
            when (cell) {
                is Cell.X -> X
                is Cell.O -> O
                is Cell.Empty -> EMPTY
            }
    }
}