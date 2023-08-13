package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.utils.getOrAwaitValue
import com.nextstep.edu.tictactoe.domain.model.Point
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TictactocViewModelTest {

    private lateinit var viewModel: TictactocViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TictactocViewModel()
    }

    @Test
    fun `같은_곳을_두번_클릭하면_WrongClick_발생한다`() {
        // when: 같은 곳을 두번 클릭한다.
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTopLeft)

        // then: WrongClick이 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.WrongClick, actual)
    }

    @Test
    fun `가로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 가로 한줄을 먼저 채운다.
        /**
         * x x x
         * o o
         *
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellTopRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.XWin, actual)
    }

    @Test
    fun `가로_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 가로 한줄을 먼저 채운다.
        /**
         * x x
         * o o o
         * x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellBottomLeft)
        onSetBoardPoint(Point.CellMiddleRight)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.OWin, actual)
    }

    @Test
    fun `세로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 세로 한줄을 먼저 채운다.
        /**
         * x o
         * x o
         * x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellBottomLeft)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.XWin, actual)
    }

    @Test
    fun `세로_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 세로 한줄을 먼저 채운다.
        /**
         * x o x
         * x o
         *   o
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellTopRight)
        onSetBoardPoint(Point.CellBottom)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.OWin, actual)
    }

    @Test
    fun `대각선_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // when: 아래와 같이 x가 대각선 한줄을 먼저 채운다.
        /**
         * x o
         * o x
         *     x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellBottomRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.XWin, actual)
    }

    @Test
    fun `대각선_한줄을_o가_먼저_채우면_o가_승리한다`() {
        // when: 아래와 같이 o가 세로 한줄을 먼저 채운다.
        /**
         * x x o
         * x o
         * o
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTopRight)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellBottomLeft)

        // then: o가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.OWin, actual)
    }

    @Test
    fun `결판이_안나면_Tie가_발생한다`() {
        // when: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTopRight)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddleRight)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellBottomLeft)
        onSetBoardPoint(Point.CellBottom)
        onSetBoardPoint(Point.CellBottomRight)

        // then: Tie가 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.Tie, actual)
    }

    @Test
    fun `승자가_결정된_후_클릭을_하면_GameOver가_발생한다`() {
        // given: 아래와 같이 x가 가로 한줄을 먼저 채워 승리했다.
        /**
         * x x x
         * o o
         *
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellTopRight)

        // when: 승자가 결정된 후 클릭을 한다.
        onSetBoardPoint(Point.CellBottomRight)

        // then: GameOver 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.GameOver, actual)
    }

    @Test
    fun `게임이_종료된_후_클릭을_하면_GameOver가_발생한다`() {
        // given: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTopRight)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddleRight)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellBottomLeft)
        onSetBoardPoint(Point.CellBottom)
        onSetBoardPoint(Point.CellBottomRight)

        // when: 게임이 종료된 후 클릭을 하면
        onSetBoardPoint(Point.CellMiddle)

        // then: GameOver 발생한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.GameOver, actual)
    }

    @Test
    fun `결판이_안나서_재시작_후_가로_한줄을_x가_먼저_채우면_x가_승리한다`() {
        // given: 아래와 같이 결판이 안난다.
        /**
         * x x o
         * o o x
         * x o x
         **/
        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellTopRight)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellMiddleRight)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellBottomLeft)
        onSetBoardPoint(Point.CellBottom)
        onSetBoardPoint(Point.CellBottomRight)

        // when: 재시작을 누르고 아래와 같이 x가 가로 한줄을 먼저 채운다.
        /**
         * x x x
         * o o
         *
         **/
        viewModel.onRestBoard()

        onSetBoardPoint(Point.CellTopLeft)
        onSetBoardPoint(Point.CellMiddleLeft)
        onSetBoardPoint(Point.CellTop)
        onSetBoardPoint(Point.CellMiddle)
        onSetBoardPoint(Point.CellTopRight)

        // then: x가 승리한다.
        val actual = viewModel.tictactocToastMessage.getOrAwaitValue()
        assertEquals(TictactocToastMessage.XWin, actual)
    }

    private fun onSetBoardPoint(point: Point) {
        viewModel.onSetBoardPoint(point)
    }
}