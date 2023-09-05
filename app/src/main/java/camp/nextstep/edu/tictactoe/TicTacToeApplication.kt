/**
 * @author Daewon on 03,September,2023
 *
 */

package camp.nextstep.edu.tictactoe

import android.app.Application
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.di.DomainModule

class TicTacToeApplication : Application() {
    val ticTacToeManager by lazy { DomainModule.provideTicTacToeManager() }
    val ticTacToe by lazy { DomainModule.provideTicTacToe(Mode.PLAYER) }
}
