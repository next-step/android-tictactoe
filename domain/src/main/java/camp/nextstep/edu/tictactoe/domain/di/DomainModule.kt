package camp.nextstep.edu.tictactoe.domain.di

import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.manager.DefaultTicTacToeManager
import camp.nextstep.edu.tictactoe.domain.manager.TicTacToeManager
import camp.nextstep.edu.tictactoe.domain.tictactoe.DefaultTicTacToe
import camp.nextstep.edu.tictactoe.domain.tictactoe.TicTacToe

object DomainModule {
    fun provideTicTacToeManager(initMode: Mode): TicTacToeManager {
        return DefaultTicTacToeManager(initMode)
    }

    fun provideTicTacToe(): TicTacToe {
        return DefaultTicTacToe()
    }
}
