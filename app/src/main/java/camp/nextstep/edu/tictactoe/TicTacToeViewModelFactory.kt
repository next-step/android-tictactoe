/**
 * @author Daewon on 03,September,2023
 *
 */

package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.manager.TicTacToeManager
import camp.nextstep.edu.tictactoe.domain.tictactoe.TicTacToe


class TicTacToeViewModelFactory(
    private val ticTacToeManager: TicTacToeManager,
    private val ticTacToe: TicTacToe
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TicTacToeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TicTacToeViewModel(ticTacToeManager, ticTacToe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

