package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.model.GameMode
import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.model.TurnResultMessage
import camp.nextstep.edu.tictactoe.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import org.junit.After
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
        viewModel.changeMode(GameMode.PlAYER_MODE)
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
        viewModel.put(Position.CellTopLeft) //x
        viewModel.put(Position.CellTop) //o
        viewModel.put(Position.CellMiddleLeft) //x
        viewModel.put(Position.CellTopRight) //o
        viewModel.put(Position.CellBottomLeft) //x
        assertThat(viewModel.showToast.getOrAwaitValue()).isEqualTo(TurnResultMessage.GameResultMessage.XWin)
    }

    /**
     *  O O O
     *      X
     *    X X
     * */
    @Test
    fun `o가 승리한다`() {
        viewModel.put(Position.CellBottomRight) //x
        viewModel.put(Position.CellTopLeft) //o
        viewModel.put(Position.CellMiddleRight) //x
        viewModel.put(Position.CellTop) //o
        viewModel.put(Position.CellBottom) //x
        viewModel.put(Position.CellTopRight) //o
        assertThat(viewModel.showToast.getOrAwaitValue()).isEqualTo(TurnResultMessage.GameResultMessage.OWin)
    }

    /**
     *  X O X
     *  X O O
     *  O X X
     * */
    @Test
    fun `무승부로 끝난다`() {
        viewModel.put(Position.CellTopLeft) //x
        viewModel.put(Position.CellTop) //o
        viewModel.put(Position.CellTopRight) //x
        viewModel.put(Position.CellMiddle) //o
        viewModel.put(Position.CellMiddleLeft) //x
        viewModel.put(Position.CellMiddleRight) //o
        viewModel.put(Position.CellBottom) //x
        viewModel.put(Position.CellBottomLeft) //o
        viewModel.put(Position.CellBottomRight) //x
        assertThat(viewModel.showToast.getOrAwaitValue()).isEqualTo(TurnResultMessage.GameResultMessage.Tie)
    }


}
