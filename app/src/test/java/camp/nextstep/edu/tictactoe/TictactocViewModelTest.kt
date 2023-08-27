package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.model.TictactocCell
import camp.nextstep.edu.tictactoe.utils.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import com.nextstep.edu.tictactoe.domain.model.GameMode
import com.nextstep.edu.tictactoe.domain.usecase.GetTictactoeModeUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
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
class TictactocViewModelTest {

    private lateinit var viewModel: TictactocViewModel

    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var getTictactoeModeUseCase: GetTictactoeModeUseCase

    @Before
    fun setUp() {
        hiltrule.inject()
        viewModel = TictactocViewModel(getTictactoeModeUseCase)
        viewModel.onSetGameMode(gameMode = GameMode.TWO_PLAYER)
    }

    @Test
    fun `같은_곳을_두번_클릭하면_WrongClick_발생한다`() {
        // when: 같은 곳을 두번 클릭한다.
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTopLeft)

        // then: WrongClick이 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.WrongClick)
    }

    @Test
    fun `가로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 가로 한줄을 먼저 채운다.
        /**
         * x x x
         * o o
         *
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellTopRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.XWin)
    }

    @Test
    fun `가로_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 가로 한줄을 먼저 채운다.
        /**
         * x x
         * o o o
         * x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellBottomLeft)
        onSetBoardPoint(TictactocCell.CellMiddleRight)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.OWin)
    }

    @Test
    fun `세로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 세로 한줄을 먼저 채운다.
        /**
         * x o
         * x o
         * x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellBottomLeft)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.XWin)
    }

    @Test
    fun `세로_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 세로 한줄을 먼저 채운다.
        /**
         * x o x
         * x o
         *   o
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellTopRight)
        onSetBoardPoint(TictactocCell.CellBottom)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.OWin)
    }

    @Test
    fun `대각선_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 대각선 한줄을 먼저 채운다.
        /**
         * x o
         * o x
         *     x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellBottomRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.XWin)
    }

    @Test
    fun `대각선_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 세로 한줄을 먼저 채운다.
        /**
         * x x o
         * x o
         * o
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTopRight)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellBottomLeft)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.OWin)
    }

    @Test
    fun `결판이_안나면_Tie가_발생한다`() {
        // when: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTopRight)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddleRight)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellBottomLeft)
        onSetBoardPoint(TictactocCell.CellBottom)
        onSetBoardPoint(TictactocCell.CellBottomRight)

        // then: Tie가 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.Tie)
    }

    @Test
    fun `승자가_결정된_후_클릭을_하면_GameOver가_발생한다`() {
        // given: 아래와 같이 x가 가로 한줄을 먼저 채워 승리했다.
        /**
         * x x x
         * o o
         *
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellTopRight)

        // when: 승자가 결정된 후 클릭을 한다.
        onSetBoardPoint(TictactocCell.CellBottomRight)

        // then: GameOver 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.GameOver)
    }

    @Test
    fun `게임이_종료된_후_클릭을_하면_GameOver가_발생한다`() {
        // given: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTopRight)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddleRight)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellBottomLeft)
        onSetBoardPoint(TictactocCell.CellBottom)
        onSetBoardPoint(TictactocCell.CellBottomRight)

        // when: 게임이 종료된 후 클릭을 하면
        onSetBoardPoint(TictactocCell.CellMiddle)

        // then: GameOver 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.GameOver)
    }

    @Test
    fun `결판이_안나서_재시작_후_가로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // given: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellTopRight)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellMiddleRight)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellBottomLeft)
        onSetBoardPoint(TictactocCell.CellBottom)
        onSetBoardPoint(TictactocCell.CellBottomRight)

        // when: 재시작을 누르고 아래와 같이 x가 가로 한줄을 먼저 채운다.
        /**
         * x x x
         * o o
         *
         **/
        viewModel.onRestBoard()

        onSetBoardPoint(TictactocCell.CellTopLeft)
        onSetBoardPoint(TictactocCell.CellMiddleLeft)
        onSetBoardPoint(TictactocCell.CellTop)
        onSetBoardPoint(TictactocCell.CellMiddle)
        onSetBoardPoint(TictactocCell.CellTopRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue().peek()
        assertThat(actual).isEqualTo(TictactocToastMessage.XWin)
    }

    private fun onSetBoardPoint(tictactocCell: TictactocCell) {
        viewModel.onSetBoardPoint(tictactocCell = tictactocCell)
    }
}