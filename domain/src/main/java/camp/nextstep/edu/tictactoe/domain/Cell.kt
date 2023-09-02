/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain


sealed class Cell(open val position: Position) {
    data class Empty(override val position: Position) : Cell(position)
    data class O(override val position: Position) : Cell(position)
    data class X(override val position: Position) : Cell(position)
}
