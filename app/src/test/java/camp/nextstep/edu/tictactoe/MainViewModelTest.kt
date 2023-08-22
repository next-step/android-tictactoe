package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToe
import camp.nextstep.tictactoe.domain.usecase.GetGameStatusUseCase
import camp.nextstep.tictactoe.domain.usecase.MarkBoardUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()

	private lateinit var markBoardUseCase: MarkBoardUseCase
	private lateinit var getGetGameStatusUseCase: GetGameStatusUseCase
	private lateinit var mainViewModel: MainViewModel

	@Before
	fun setUp() {
		markBoardUseCase = mockk(relaxed = true)
		getGetGameStatusUseCase = mockk(relaxed = true)
		mainViewModel = MainViewModel(markBoardUseCase, getGetGameStatusUseCase)
	}

	@Test
	fun `특정 좌표를 mark 하면, TicTacToe 를 갱신한다`() = runBlocking {
		// given
		val expected = TicTacToe(board = Board(map = mapOf(Point(0, 0) to Marker.X)))
		every { markBoardUseCase.invoke(Point(0, 0), any()) } returns expected

		// when
		mainViewModel.mark(0, 0)

		// then
		verify { markBoardUseCase.invoke(Point(0, 0), any()) }
		mainViewModel.ticTaeToc.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(expected)
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 승자가 나왔다면, 승자를 전달한다`() = runBlocking {
		// given
		every { getGetGameStatusUseCase.invoke(any()) } returns GameStatus.End(Marker.X)

		// when
		mainViewModel.mark(0, 0)

		// then
		verify { getGetGameStatusUseCase.invoke(any()) }
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
		}
	}

	@Test
	fun `특정 좌표를 mark 했을 때, 무승부면, 무승부임을 전달한다`() = runBlocking {
		// given
		every { getGetGameStatusUseCase.invoke(any()) } returns GameStatus.Draw

		// when
		mainViewModel.mark(0, 0)

		// then
		verify { getGetGameStatusUseCase.invoke(any()) }
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.Draw)
		}
	}

	@Test
	fun `게임을 다시 시작하면, Board 와 Player 가 초기화된다`() = runBlocking {
		// when
		mainViewModel.restartGame()

		// then
		mainViewModel.ticTaeToc.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(TicTacToe.INIT)
		}
	}

	@Test
	fun `게임을 다시 시작하면, 게임 상태가 InProgress 상태로 설정된다`() = runBlocking {
		// when
		mainViewModel.restartGame()

		// then
		mainViewModel.gameStatus.test {
			val actual = awaitItem()
			assertThat(actual).isEqualTo(GameStatus.InProgress)
		}
	}
}