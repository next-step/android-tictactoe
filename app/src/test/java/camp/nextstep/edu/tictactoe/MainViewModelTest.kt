package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Point
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()

	private lateinit var mainViewModel: MainViewModel

	@Before
	fun setUp() {
		mainViewModel = MainViewModel()
	}

	@Test
	fun `특정 좌표를 mark 하면, 특정 좌표가 mark 된 Board 로 갱신한다`() = runBlocking {
		// when
		mainViewModel.mark(Point(0, 0))

		// then
		mainViewModel.board.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(Board(map = mapOf(Point(0, 0) to Marker.X)))
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 승자가 나왔다면, 승자를 전달한다`() {
		setBoardToEndGame()

		// then
		val actual = mainViewModel.showWinner.getOrAwaitValue()
		assertThat(actual).isEqualTo(Marker.X)
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 무승부면, 무승부임을 전달한다`() = runBlocking {
		setBoardToDrawGame()

		// then
		val actual1 = mainViewModel.showDraw.getOrAwaitValue()
		assertThat(actual1).isEqualTo(Unit)

	}

	@Test
	fun `특정 좌표를 mark 했을 때, 승자가 나왔다면, Board 를 초기화한다`() = runBlocking {
		setBoardToEndGame()

		// then
		mainViewModel.board.test {
			val actual2 = awaitItem()
			assertThat(actual2).isEqualTo(Board.EMPTY)
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 무승부면, Board 를 초기화한다`() = runBlocking {
		setBoardToDrawGame()

		// then
		mainViewModel.board.test {
			val actual2 = awaitItem()
			assertThat(actual2).isEqualTo(Board.EMPTY)
		}
	}

	private fun setBoardToEndGame() {
		// given
		mainViewModel.mark(Point(0, 0))
		mainViewModel.mark(Point(1, 0))
		mainViewModel.mark(Point(0, 1))
		mainViewModel.mark(Point(2, 0))

		// when
		mainViewModel.mark(Point(0, 2))
	}

	private fun setBoardToDrawGame() {
		// given
		mainViewModel.mark(Point(0, 0))
		mainViewModel.mark(Point(0, 1))
		mainViewModel.mark(Point(0, 2))
		mainViewModel.mark(Point(1, 0))
		mainViewModel.mark(Point(1, 1))
		mainViewModel.mark(Point(2, 0))
		mainViewModel.mark(Point(1, 2))
		mainViewModel.mark(Point(2, 2))

		// when
		mainViewModel.mark(Point(2, 1))
	}
}