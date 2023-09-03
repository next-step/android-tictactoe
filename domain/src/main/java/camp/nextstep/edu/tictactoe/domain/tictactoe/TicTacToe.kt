/**
 * @author Daewon on 03,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain.tictactoe

import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.Turn

interface TicTacToe {
    fun mark(position: Position): Result<Unit>
    fun getBoard(): Board
    fun currentTurn(): Turn
    fun restart()
}
