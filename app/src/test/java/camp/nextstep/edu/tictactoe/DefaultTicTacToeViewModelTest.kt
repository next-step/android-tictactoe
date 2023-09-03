package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.GameStatus
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.Turn
import camp.nextstep.edu.tictactoe.domain.manager.TicTacToeManager
import camp.nextstep.edu.tictactoe.domain.tictactoe.TicTacToe
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DefaultTicTacToeViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TicTacToeViewModel
    private val manager: TicTacToeManager = mockk(relaxed = true)
    private val ticTacToe: TicTacToe = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = TicTacToeViewModel(manager, ticTacToe)
    }

    @Test
    fun `하나의 좌표를 marking 하면, 해당 좌표에 marking 되어야 한다`() {
        // given
        every { ticTacToe.mark(Position.TOP_RIGHT) } returns Result.success(Unit)

        // when
        viewModel.onClickMark(Position.TOP_RIGHT)

        // then
        verify { ticTacToe.mark(Position.TOP_RIGHT) }
    }

    @Test
    fun `승리결과가 나왔다면, 승리 결과를 전달한다`() {
        // given
        every { ticTacToe.mark(Position.TOP_LEFT) } returns Result.success(Unit)
        every { manager.checkGameStatus(any()) } returns GameStatus.WinX
        viewModel.onClickMark(Position.TOP_LEFT)

        // when
        val actual = viewModel.uiState.getOrAwaitValue().turn

        // then
        assertThat(actual).isEqualTo(Turn.X)
        verify { manager.checkGameStatus(any()) }
    }

    @Test
    fun `게임을 다시 시작하면, Board가 초기화 된다`() {
        // given
        every { ticTacToe.mark(any()) } returns Result.success(Unit)
        every { ticTacToe.getBoard() } returns Board.EMPTY
        viewModel.onClickMark(Position.CENTER_CENTER)

        // when
        viewModel.onClickRestart()

        // then
        val actual = viewModel.uiState.getOrAwaitValue().board
        assertThat(actual).isEqualTo(Board.EMPTY)
        verify { ticTacToe.restart() }
    }
}
