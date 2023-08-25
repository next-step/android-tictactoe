package camp.nextstep.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DefaultTicTaeToHandlerTest {

	private lateinit var defaultTicTaeToHandler: DefaultTicTaeToHandler

	@Before
	fun setUp() {
		defaultTicTaeToHandler = DefaultTicTaeToHandler()
	}

	@Test
	fun `mark 하면, 갱신된 TicTaeToe 를 반환한다`() {
		// given
		val expected = TicTacToe(
			mode = Mode.TwoPerson,
			board = Board(map = mapOf(Point(0, 0) to Marker.X)),
			player = Player.Person(Marker.O)
		)

		// when
		val actual = defaultTicTaeToHandler.mark(Point(0, 0), TicTacToe.create(Mode.TwoPerson))

		// then
		assertThat(actual).isEqualTo(expected)
	}

	@Test
	fun `한줄이 꽉찬 Board 의 게임 상태를 가져오면, End 게임 상태를 반환한다`() {
		// given
		/**
		 * X . .
		 * X . .
		 * X . .
		 */
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(1, 0) to Marker.X,
				Point(2, 0) to Marker.X,
			)
		)

		// when
		val actual = defaultTicTaeToHandler.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.End(Marker.X))
	}

	@Test
	fun `모든 줄이 꽉찬 Board 의 게임 상태를 가져오면, Draw 게임 상태를 반환한다`() {
		// given
		/**
		 * X O X
		 * O X X
		 * O X O
		 */
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
			)
		)

		// when
		val actual = defaultTicTaeToHandler.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.Draw)
	}

	@Test
	fun `어느 줄도 꽉 차지 않은 Board 의 게임 상태를 가져오면, InProgress 게임 상태를 반환한다`() {
		// given
		/**
		 * X O X
		 * O X X
		 * O . .
		 */
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.O,
				Point(0, 2) to Marker.X,
				Point(1, 0) to Marker.O,
				Point(1, 1) to Marker.X,
				Point(1, 2) to Marker.X,
				Point(2, 0) to Marker.O,
			)
		)

		// when
		val actual = defaultTicTaeToHandler.getGameStatus(board)

		// then
		assertThat(actual).isEqualTo(GameStatus.InProgress)
	}

	@Test
	fun `Player 가 AI 일때, 랜덤으로 mark 하면, 갱신된 TicTaeToe 를 반환한다`() {
		// given
		val ticTacToe = TicTacToe(
			mode = Mode.Random,
			player = Player.RandomAi(),
			board = Board.EMPTY
		)

		// when
		val actual = defaultTicTaeToHandler.markRandomlyIfNeed(ticTacToe)

		// then
		assertThat(actual).isNotEqualTo(ticTacToe)
	}

	@Test
	fun `Player 가 AI 가 아닐때, 랜덤으로 mark 하면, TicTaeToe 를 그대로 반환한다`() {
		// given
		val ticTacToe = TicTacToe(
			mode = Mode.Random,
			player = Player.Person(),
			board = Board.EMPTY
		)

		// when
		val actual = defaultTicTaeToHandler.markRandomlyIfNeed(ticTacToe)

		// then
		assertThat(actual).isEqualTo(ticTacToe)
	}
}