package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class TicTacToeViewModelTest {

    lateinit var viewModel: TicTacToeViewModel

    @get:Rule(order = 0)
    var hiltrule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var game: Game

    @Before
    fun setUp() {
        hiltrule.inject()
        viewModel = TicTacToeViewModel(game)
    }

    @Test
    fun `짝수에 두는 경우 X 블록을 추가한다`() {
        // given
        viewModel.changeGameMode(SelectMode.TwoPlayer)
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
        viewModel.changeGameMode(SelectMode.TwoPlayer)
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
        viewModel.changeGameMode(SelectMode.TwoPlayer)
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
        viewModel.changeGameMode(SelectMode.TwoPlayer)
        viewModel.assign(0)
        viewModel.assign(1)
        viewModel.assign(3)
        viewModel.assign(4)
        viewModel.assign(6)
        val endState = viewModel.state.getOrAwaitValue()
        assertEquals(endState.status, GameStatus.X_WON)
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
        assertEquals(state.board, BoardState(List(9) { EmptyBlock() }))
    }

    @Test
    fun `모드를 변경할 수 있다`() {
        // given
        assertTrue(viewModel.mode.getOrAwaitValue() == SelectMode.Draw)
        // when
        viewModel.changeGameMode(SelectMode.TwoPlayer)

        // then
        assertTrue(viewModel.mode.getOrAwaitValue() == SelectMode.TwoPlayer)
    }

    @Test
    fun `모드를 변경하면 게임이 초기화된다`() {
        // given
        viewModel.assign(0)


        // when
        viewModel.changeGameMode(SelectMode.TwoPlayer)

        // then
        val state = viewModel.state.getOrAwaitValue()
        assertEquals(state.status, GameStatus.ONGOING)
        assertEquals(state.board, BoardState(List(9) { EmptyBlock() }))
    }

    @Test
    fun `같은 모드로 변경할 시 에러메세지를 받는다`() {
        // when
        viewModel.changeGameMode(SelectMode.Draw)

        // then
        assertEquals(
            viewModel.exceptionMessage.getOrAwaitValue(), "같은 모드로 변경하실 수 없습니다."
        )
    }

    @Test
    fun `랜덤모드에서 플레이어가 두면 AI의 결과도 함께 나타난다`() {
        // given
        assertEquals(viewModel.state.getOrAwaitValue().board.blocks.count { it is EmptyBlock }, 9)

        // when
        viewModel.assign(0)

        // then
        assertEquals(viewModel.state.getOrAwaitValue().board.blocks.count { it is EmptyBlock }, 7)
    }
}
