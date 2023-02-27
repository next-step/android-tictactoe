package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TicTacToeViewModelTest {

    private lateinit var viewModel: TicTacToeViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `짝수에 두는 경우 X 블록을 추가한다`() {
        // given
        viewModel = TicTacToeViewModel()
        val blockIndex = 0

        // when
        viewModel.assign(blockIndex)

        // then
        assertEquals(
            viewModel.state.getOrAwaitValue().board.blocks,
            listOf(
                XBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )
    }

    @Test
    fun `홀수에 두는 경우 O 블록을 추가한다`() {
        // given
        viewModel = TicTacToeViewModel()
        viewModel.assign(0)
        assertEquals(
            viewModel.state.getOrAwaitValue().board.blocks,
            listOf(
                XBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )

        // when
        viewModel.assign(1)

        // then
        assertEquals(
            viewModel.state.getOrAwaitValue().board.blocks,
            listOf(
                XBlock,
                OBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )
    }

    @Test
    fun `둔 곳에 또 두면 에러메세지를 받는다`() {
        // given
        viewModel = TicTacToeViewModel()
        viewModel.assign(0)

        // when
        viewModel.assign(0)

        // then
        assertEquals(
            viewModel.exceptionMessage.getOrAwaitValue(), "이미 놓여진 블록입니다."
        )
    }

    @Test
    fun `게임이 끝난 경우에 또 두면 에러메세지를 받는다`() {
        // given
        viewModel = TicTacToeViewModel()
        viewModel.assign(0)
        viewModel.assign(1)
        viewModel.assign(3)
        viewModel.assign(4)
        viewModel.assign(6)

        // when
        viewModel.assign(5)

        // then
        assertEquals(
            viewModel.exceptionMessage.getOrAwaitValue(), "게임이 종료되었습니다."
        )
    }

    @Test
    fun `reset을 누르면 초기화된다`() {
        // given
        viewModel = TicTacToeViewModel()
        viewModel.assign(0)
        viewModel.assign(1)
        viewModel.assign(3)
        viewModel.assign(4)
        viewModel.assign(6)
        val endState = viewModel.state.getOrAwaitValue()
        assertEquals(endState.status, GameStatus.X_WON)
        assertEquals(endState.turn, Turn(4))
        assertEquals(
            endState.board,
            BoardState(
                listOf(
                    XBlock,
                    OBlock,
                    EmptyBlock(),
                    XBlock,
                    OBlock,
                    EmptyBlock(),
                    XBlock,
                    EmptyBlock(),
                    EmptyBlock()
                )
            )
        )

        // when
        viewModel.reset()

        // then
        val state = viewModel.state.getOrAwaitValue()
        assertEquals(state.status, GameStatus.ONGOING)
        assertEquals(state.turn, Turn(0))
        assertEquals(state.board, BoardState(List(9) { EmptyBlock() }))
    }
}
