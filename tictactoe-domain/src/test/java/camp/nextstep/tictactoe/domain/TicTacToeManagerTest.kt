package camp.nextstep.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TicTacToeManagerTest {

	private lateinit var ticTacToeManager: TicTacToeManager

	@Before
	fun setUp() {
		ticTacToeManager = TicTacToeManager(Mode.TwoPerson)
	}

	@Test
	fun `Player 가 Person 일 때, mark 하면, mark 된 Board 를 반환한다`() {
		// when
		val actual = ticTacToeManager.mark(Point(0, 0), Board.EMPTY)

		// then
		assertThat(actual).isEqualTo(Board(map = mapOf(Point(0, 0) to Marker.X)))
	}

	@Test
	fun `행 한줄이 X 표시로 채워졌을 때, 게임 상태를 가져오면, 게임 종료 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.X,
				Point(0, 2) to Marker.X,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
	}

	@Test
	fun `열 한줄이 X 표시로 채워졌을 때, 게임 상태를 가져오면, 게임 종료 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(1, 0) to Marker.X,
				Point(2, 0) to Marker.X,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
	}

	@Test
	fun `왼쪽에서 오른쪽으로의 대각선 한줄이 X 표시로 채워졌을 때, 게임 상태를 가져오면, 게임 종료 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(1, 1) to Marker.X,
				Point(2, 2) to Marker.X,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
	}

	@Test
	fun `오른쪽에서 왼쪽으로의 대각선 한줄이 X 표시로 채워졌을 때, 게임 상태를 가져오면, 게임 종료 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 2 ) to Marker.X,
				Point(1, 1) to Marker.X,
				Point(2, 0) to Marker.X,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
	}

	@Test
	fun `어느 한줄도 X,O 표시로 채워지지 않았을 때, 게임 상태를 가져오면, 진행중 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.O,
				Point(1, 0) to Marker.X,
				Point(1, 1) to Marker.O,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.InProgress)
	}

	@Test
	fun `모든 줄이 X,O 표시로 채워졌을 때, 게임 상태를 가져오면, 무승부 상태를 반환한다`() {
		// given
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.O,
				Point(0, 2) to Marker.X,
				Point(1, 0) to Marker.O,
				Point(1, 1) to Marker.X,
				Point(1, 2) to Marker.X,
				Point(2, 0) to Marker.O,
				Point(2, 1) to Marker.X,
				Point(2, 2) to Marker.O,
			))

		// when
		val actual = ticTacToeManager.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.Draw)
	}
}