package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTaeToHandler
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()

	private lateinit var ticTaeToHandler: TicTaeToHandler
	private lateinit var mainViewModel: MainViewModel

	@Before
	fun setUp() {
		ticTaeToHandler = mockk(relaxed = true)
		mainViewModel = MainViewModel(ticTaeToHandler)
	}

	@Test
	fun `2인모드일때, 특정 좌표를 mark 하면, 특정 좌표를 mark 한다`() {
		// given
		mainViewModel.updateMode(Mode.TwoPerson)

		// when
		mainViewModel.mark(0, 0)

		// given
		verify { ticTaeToHandler.mark(Point(0, 0), any()) }
	}

	@Test
	fun `2인모드일때, 특정 좌표를 mark 하면, 게임 상태를 가져온다`() {
		// given
		mainViewModel.updateMode(Mode.TwoPerson)

		// when
		mainViewModel.mark(0, 0)

		// given
		verify { ticTaeToHandler.getGameStatus(any()) }
	}

	@Test
	fun `랜덤모드일때, 특정 좌표를 mark 하면, 특정 좌표를 mark 하고 랜덤 좌표를 mark 한다`() {
		// given
		mainViewModel.updateMode(Mode.Random)
		every { ticTaeToHandler.getGameStatus(any()) } returns GameStatus.InProgress

		// when
		mainViewModel.mark(0, 0)

		// given
		verify { ticTaeToHandler.mark(Point(0, 0), any()) }
		verify { ticTaeToHandler.getGameStatus(any()) }
		verify { ticTaeToHandler.markRandomlyIfNeed(any(), any()) }
	}

	@Test
	fun `랜덤모드이고, 특정 좌표를 mark 했을때, 승자가 나오면, 특정 좌표를 mark 하고 랜덤 좌표는 mark 하지 않는다`() {
		// given
		mainViewModel.updateMode(Mode.Random)
		every { ticTaeToHandler.getGameStatus(any()) } returns GameStatus.End(Marker.X)

		// when
		mainViewModel.mark(0, 0)

		// given
		verify { ticTaeToHandler.mark(Point(0, 0), any()) }
		verify(inverse = true) { ticTaeToHandler.markRandomlyIfNeed(any(), any()) }
		verify(exactly = 1) { ticTaeToHandler.getGameStatus(any()) }
	}
}