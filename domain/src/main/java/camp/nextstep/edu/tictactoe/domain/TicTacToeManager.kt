/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain

class TicTacToeManager(
    initMode: Mode
) {

    private var mode: Mode = initMode

    fun checkGameStatus(board: Board): GameStatus {
        checkWinner(board)?.let {
            return when (it) {
                Turn.X -> GameStatus.WinX
                Turn.O -> GameStatus.WinO
            }
        }

        return if (isDraw(board)) {
            GameStatus.Draw
        } else {
            GameStatus.InProgress
        }
    }

    private fun checkWinner(board: Board): Turn? {
        // 행
        for (row in 0 until BOARD_SIZE) {
            checkWinner(board) { position -> position.ordinal.div(BOARD_SIZE) == row }
                ?.let { return it }
        }

        // 열
        for (col in 0 until BOARD_SIZE) {
            checkWinner(board) { position -> position.ordinal % BOARD_SIZE == col }
                ?.let { return it }
        }

        // 오른쪽 대각선
        checkWinner(board) { position -> Position.getRow(position) == Position.getColumn(position) }
            ?.let { return it }

        // 왼쪽 대각선
        checkWinner(board) { position -> Position.getRow(position) + Position.getColumn(position) == BOARD_SIZE - 1 }
            ?.let { return it }

        return null
    }

    private fun checkWinner(board: Board, condition: (Position) -> Boolean): Turn? {
        return when {
            hasWinner(board) { (position, cell) ->
                condition(position) && cell == Cell.X(position)
            } -> Turn.X

            hasWinner(board) { (position, cell) ->
                condition(position) && cell == Cell.O(position)
            } -> Turn.O

            else -> null
        }
    }

    private fun hasWinner(
        board: Board,
        predicate: (Map.Entry<Position, Cell>) -> Boolean
    ): Boolean {
        return board.filter(predicate).count() == BOARD_SIZE
    }

    private fun isDraw(board: Board): Boolean {
        return board.boardSize == BOARD_SIZE * BOARD_SIZE
    }
}



