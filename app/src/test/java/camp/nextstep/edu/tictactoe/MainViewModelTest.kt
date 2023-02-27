package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.model.Point
import camp.nextstep.edu.tictactoe.model.TurnResultMessage
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

    /**
     *  X O O
     *  X
     *  X
     * */
    @Test
    fun `x가 승리한다`() {
        viewModel.put(Point.CellTopLeft) //x
        viewModel.put(Point.CellTop) //o
        viewModel.put(Point.CellMiddleLeft) //x
        viewModel.put(Point.CellTopRight) //o
        viewModel.put(Point.CellBottomLeft) //x
        assertEquals(
            TurnResultMessage.GameResultMessage.XWin,
            viewModel.showToast.getOrAwaitValue()
        )
    }

    /**
     *  O O O
     *      X
     *    X X
     * */
    @Test
    fun `o가 승리한다`() {
        viewModel.put(Point.CellBottomRight) //x
        viewModel.put(Point.CellTopLeft) //o
        viewModel.put(Point.CellMiddleRight) //x
        viewModel.put(Point.CellTop) //o
        viewModel.put(Point.CellBottom) //x
        viewModel.put(Point.CellTopRight) //o
        assertEquals(
            TurnResultMessage.GameResultMessage.OWin,
            viewModel.showToast.getOrAwaitValue()
        )
    }

    /**
     *  X O X
     *  X O O
     *  O X X
     * */
    @Test
    fun `무승부로 끝난다`() {
        viewModel.put(Point.CellTopLeft) //x
        viewModel.put(Point.CellTop) //o
        viewModel.put(Point.CellTopRight) //x
        viewModel.put(Point.CellMiddle) //o
        viewModel.put(Point.CellMiddleLeft) //x
        viewModel.put(Point.CellMiddleRight) //o
        viewModel.put(Point.CellBottom) //x
        viewModel.put(Point.CellBottomLeft) //o
        viewModel.put(Point.CellBottomRight) //x
        assertEquals(TurnResultMessage.GameResultMessage.Tie, viewModel.showToast.getOrAwaitValue())
    }


}