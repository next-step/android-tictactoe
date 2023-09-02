/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain


enum class Position {
    TOP_LEFT, TOP_CENTER, TOP_RIGHT,
    CENTER_LEFT, CENTER_CENTER, CENTER_RIGHT,
    BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;

    companion object {
        fun getRow(position: Position): Int {
            return position.ordinal / 3
        }

        fun getColumn(position: Position): Int {
            return position.ordinal % 3
        }
    }
}
