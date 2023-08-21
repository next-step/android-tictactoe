package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.GameStatus
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
		mainViewModel.mark(0, 0)

		// then
		mainViewModel.board.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(Board(map = mapOf(Point(0, 0) to Marker.X)))
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 승자가 나왔다면, 승자를 전달한다`() = runBlocking {
		setBoardToEndGame()

		// then
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 무승부면, 무승부임을 전달한다`() = runBlocking {
		setBoardToDrawGame()

		// then
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.Draw)
		}
	}

	@Test
	fun `게임을 다시 시작하면, 게임 상태가 InProgress 상태로 설정된다`() = runBlocking {
		// given
		mainViewModel.mark(0, 0)

		// when
		mainViewModel.restartGame()

		// then
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.InProgress)
		}
	}

	@Test
	fun `게임을 다시 시작하면, Board 가 초기화된다`() = runBlocking {
		// given
		mainViewModel.mark(0, 0)

		// when
		mainViewModel.restartGame()

		// then
		mainViewModel.board.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(Board.EMPTY)
		}
	}

	private fun setBoardToEndGame() {
		// given
		mainViewModel.mark(0, 0)
		mainViewModel.mark(1, 0)
		mainViewModel.mark(0, 1)
		mainViewModel.mark(2, 0)

		// when
		mainViewModel.mark(0, 2)
	}

	private fun setBoardToDrawGame() {
		// given
		mainViewModel.mark(0, 0)
		mainViewModel.mark(0, 1)
		mainViewModel.mark(0, 2)
		mainViewModel.mark(1, 0)
		mainViewModel.mark(1, 1)
		mainViewModel.mark(2, 0)
		mainViewModel.mark(1, 2)
		mainViewModel.mark(2, 2)

		// when
		mainViewModel.mark(2, 1)
	}
}