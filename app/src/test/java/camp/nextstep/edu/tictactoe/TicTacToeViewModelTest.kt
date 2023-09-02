package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.Turn
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TicTacToeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TictactoeViewModel

    @Before
    fun setUp() {
        viewModel = TictactoeViewModel()
    }

    @Test
    fun `하나의 좌표를 marking 하면, 해당 좌표에 marking 되어야 한다`() {
        // given
        viewModel.onClickMark(Position.TOP_RIGHT)

        // when
        val actual = (viewModel.uiState.getOrAwaitValue() as? UiState.Inprogress)?.board

        // then
        assertThat(actual?.get(Position.TOP_RIGHT)).isEqualTo(Cell.X(Position.TOP_RIGHT))
    }

    @Test
    fun `승리결과가 나왔다면, 승리 결과를 전달한다`() {
        // given
        setEndGame()

        // when
        val actual = (viewModel.uiState.getOrAwaitValue() as? UiState.End)?.turn

        // then
        assertThat(actual).isEqualTo(Turn.X)
    }

    @Test
    fun `게임을 다시 시작하면, Board가 초기화 된다`() {
        // given
        viewModel.onClickMark(Position.CENTER_CENTER)

        // when
        viewModel.onClickRestart()

        // then
        val actual = (viewModel.uiState.getOrAwaitValue() as? UiState.Inprogress)?.board
        assertThat(actual).isEqualTo(Board.EMPTY)
    }

    private fun setEndGame() {
        // given
        viewModel.onClickMark(Position.TOP_LEFT)
        viewModel.onClickMark(Position.TOP_CENTER)
        viewModel.onClickMark(Position.CENTER_LEFT)
        viewModel.onClickMark(Position.CENTER_CENTER)

        // when
        viewModel.onClickMark(Position.BOTTOM_LEFT)
    }
}
