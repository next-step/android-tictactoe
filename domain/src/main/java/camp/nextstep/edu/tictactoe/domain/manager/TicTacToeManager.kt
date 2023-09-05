/**
 * @author Daewon on 03,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain.manager

import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.GameStatus
import camp.nextstep.edu.tictactoe.domain.Mode


interface TicTacToeManager {
    fun checkGameStatus(board: Board): GameStatus
    fun isDraw(board: Board): Boolean
}
