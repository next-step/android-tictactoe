package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.Point
import camp.nextstep.edu.tictactoe.model.MessageState
import camp.nextstep.edu.tictactoe.util.getOrAwaitValue
import io.mockk.clearAllMocks
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MainViewModel()

    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `x가 승리한다`() {
        viewModel.isWin(Point(0, 0)) //x
        viewModel.isWin(Point(0, 1)) //o
        viewModel.isWin(Point(1, 0)) //x
        viewModel.isWin(Point(0, 2)) //o
        viewModel.isWin(Point(2, 0)) //x
        assertEquals(MessageState.X_WIN, viewModel.showToast.getOrAwaitValue())
    }

    @Test
    fun `o가 승리한다`() {
        viewModel.isWin(Point(2, 2)) //x
        viewModel.isWin(Point(0, 0)) //o
        viewModel.isWin(Point(1, 2)) //x
        viewModel.isWin(Point(0, 1)) //o
        viewModel.isWin(Point(2, 1)) //x
        viewModel.isWin(Point(0, 2)) //o
        assertEquals(MessageState.O_WIN, viewModel.showToast.getOrAwaitValue())
    }

    @Test
    fun `무승부로 끝난다`() {
        viewModel.isWin(Point(0, 0)) //x
        viewModel.isWin(Point(0, 1)) //o
        viewModel.isWin(Point(0, 2)) //x
        viewModel.isWin(Point(1, 1)) //o
        viewModel.isWin(Point(1, 0)) //x
        viewModel.isWin(Point(1, 2)) //o
        viewModel.isWin(Point(2, 1)) //x
        viewModel.isWin(Point(2, 0)) //o
        viewModel.isWin(Point(2, 2)) //x
        assertEquals(MessageState.IN_A_TIE, viewModel.showToast.getOrAwaitValue())
    }

}