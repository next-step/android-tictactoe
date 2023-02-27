package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TicTacToeViewModelTest {

    private var viewModel: TicTacToeViewModel = TicTacToeViewModel()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
}
