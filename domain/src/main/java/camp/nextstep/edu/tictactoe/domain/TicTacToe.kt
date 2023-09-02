/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain


class TicTacToe(
    private var currentTurn: Turn = Turn.X,
) {
    var board: Board = Board.EMPTY
        private set

    fun mark(position: Position) = runCatching {
        board = when (currentTurn) {
            Turn.X -> {
                board.set(position, Cell.X(position))
            }

            Turn.O -> {
                board.set(position, Cell.O(position))
            }
        }
    }.onSuccess { changeTurn() }

    private fun changeTurn() {
        currentTurn = Turn.change(currentTurn)
    }

    fun currentTurn(): Turn {
        return currentTurn
    }

    fun restart() {
        board = Board.EMPTY
        currentTurn = Turn.X
    }
}
