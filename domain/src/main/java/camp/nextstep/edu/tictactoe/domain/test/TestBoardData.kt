/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain.test

import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Position

fun getWinXBoard(): Board {
    return Board(
        mapOf(
            Position.TOP_LEFT to Cell.X(Position.TOP_LEFT),
            Position.TOP_CENTER to Cell.X(Position.TOP_CENTER),
            Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT),

            Position.CENTER_LEFT to Cell.O(Position.CENTER_LEFT),
            Position.CENTER_CENTER to Cell.O(Position.CENTER_CENTER),
            Position.CENTER_RIGHT to Cell.X(Position.CENTER_RIGHT),

            Position.BOTTOM_LEFT to Cell.O(Position.BOTTOM_LEFT),
            Position.BOTTOM_CENTER to Cell.X(Position.BOTTOM_CENTER),
            Position.BOTTOM_RIGHT to Cell.O(Position.BOTTOM_RIGHT)
        )
    )
}

fun getWinYBoard(): Board {
    return Board(
        mapOf(
            Position.TOP_LEFT to Cell.X(Position.TOP_LEFT),
            Position.TOP_CENTER to Cell.O(Position.TOP_CENTER),
            Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT),

            Position.CENTER_LEFT to Cell.O(Position.CENTER_LEFT),
            Position.CENTER_CENTER to Cell.O(Position.CENTER_CENTER),
            Position.CENTER_RIGHT to Cell.X(Position.CENTER_RIGHT),

            Position.BOTTOM_LEFT to Cell.O(Position.BOTTOM_LEFT),
            Position.BOTTOM_CENTER to Cell.X(Position.BOTTOM_CENTER),
            Position.BOTTOM_RIGHT to Cell.X(Position.BOTTOM_RIGHT)
        )
    )
}

fun getDrawBoard(): Board {
    return Board(
        mapOf(
            Position.TOP_LEFT to Cell.O(Position.TOP_LEFT),
            Position.TOP_CENTER to Cell.O(Position.TOP_CENTER),
            Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT),

            Position.CENTER_LEFT to Cell.X(Position.CENTER_LEFT),
            Position.CENTER_CENTER to Cell.X(Position.CENTER_CENTER),
            Position.CENTER_RIGHT to Cell.O(Position.CENTER_RIGHT),

            Position.BOTTOM_LEFT to Cell.O(Position.BOTTOM_LEFT),
            Position.BOTTOM_CENTER to Cell.X(Position.BOTTOM_CENTER),
            Position.BOTTOM_RIGHT to Cell.X(Position.BOTTOM_RIGHT)
        )
    )
}

fun getInCompleteBoard() = Board(
    mapOf(
        Position.TOP_LEFT to Cell.X(Position.TOP_LEFT),
        Position.TOP_CENTER to Cell.O(Position.TOP_CENTER),
        Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT),

        Position.CENTER_LEFT to Cell.O(Position.CENTER_LEFT),
        Position.CENTER_CENTER to Cell.O(Position.CENTER_CENTER),
        Position.CENTER_RIGHT to Cell.X(Position.CENTER_RIGHT),

        Position.BOTTOM_LEFT to Cell.O(Position.BOTTOM_LEFT),
        Position.BOTTOM_CENTER to Cell.X(Position.BOTTOM_CENTER)
    )
)
