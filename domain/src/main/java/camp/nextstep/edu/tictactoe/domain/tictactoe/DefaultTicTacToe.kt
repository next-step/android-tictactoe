/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain.tictactoe

import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.Turn

internal class DefaultTicTacToe(
    private var currentTurn: Turn = Turn.X,
    private var mode: Mode = Mode.PLAYER
) : TicTacToe {

    private var board: Board = Board.EMPTY
    override fun mark(position: Position): Result<Unit> = runCatching {
        board = when (currentTurn) {
            Turn.X -> {
                board.set(position, Cell.X(position))
            }

            Turn.O -> {
                board.set(position, Cell.O(position))
            }
        }
    }.onSuccess {
        changeTurn()
        checkCurrentMode()
    }

    private fun checkCurrentMode() {
        if(currentTurn != Turn.O) {
            return
        }
        when(mode) {
            Mode.PLAYER -> return
            Mode.RANDOM -> markRandomPosition()
        }
    }

    override fun markRandomPosition() {
        if (board.isFull()) {
            return
        }
        mark(getRandomPosition())
    }

    private fun getRandomPosition(): Position {
        return board.getEmptyPositions().random()
    }

    override fun getBoard(): Board {
        return board
    }

    private fun changeTurn() {
        currentTurn = Turn.change(currentTurn)
    }

    override fun currentTurn(): Turn {
        return currentTurn
    }

    override fun restart() {
        board = Board.EMPTY
        currentTurn = Turn.X
    }

    override fun changeMode(mode: Mode) {
        this.mode = mode
    }
}
