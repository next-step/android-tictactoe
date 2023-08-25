package camp.nextstep.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class BoardTest {

	private lateinit var board: Board

	@Before
	fun setUp() {
		board = Board.EMPTY
	}

	@Test
	fun `key 값이 존재하지 않을 때, 값을 set 하면, 값이 추가된 Board 와 함께 Success 를 반환한다`() {
		// given
		val expected = SetBoardStatus.Success(Board(map = mapOf(Point(0, 0) to Marker.X)))

		// when
		val actual = board.set(Point(0, 0), Marker.X)

		// then
		assertThat(actual).isEqualTo(expected)
	}

	@Test
	fun `key 값이 존재할 때, 값을 set 하면, AlreadyExist 를 반환한다`() {
		// given
		board = Board(map = mapOf(Point(0, 0) to Marker.X))

		// when
		val actual = board.set(Point(0, 0), Marker.O)

		// then
		assertThat(actual).isEqualTo(SetBoardStatus.AlreadyExist)
	}

	@Test
	fun `행 한줄이 X 표시로 채워졌을 때, 승자를 가져오면, 승자를 반환한다`() {
		// given
		/**
		 * X X X
		 * . . .
		 * . . .
		 */
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.X,
				Point(0, 2) to Marker.X,
			)
		)

		// when
		val actual = board.getWinner()

		// then
		assertThat(actual).isEqualTo(Marker.X)
	}

	@Test
	fun `열 한줄이 X 표시로 채워졌을 때, 승자를 가져오면, 승자를 반환한다`() {
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
		val actual = board.getWinner()

		// then
		assertThat(actual).isEqualTo(Marker.X)
	}

	@Test
	fun `왼쪽에서 오른쪽으로의 대각선 한줄이 X 표시로 채워졌을 때, 승자를 가져오면, 승자를 반환한다`() {
		// given
		/**
		 * X . .
		 * . X .
		 * . . X
		 */
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(1, 1) to Marker.X,
				Point(2, 2) to Marker.X,
			)
		)

		// when
		val actual = board.getWinner()

		// then
		assertThat(actual).isEqualTo(Marker.X)
	}

	@Test
	fun `오른쪽에서 왼쪽으로의 대각선 한줄이 X 표시로 채워졌을 때, 승자를 가져오면, 승자를 반환한다`() {
		// given
		/**
		 * . . X
		 * . X .
		 * X . .
		 */
		val board = Board(
			map = mapOf(
				Point(0, 2) to Marker.X,
				Point(1, 1) to Marker.X,
				Point(2, 0) to Marker.X,
			)
		)

		// when
		val actual = board.getWinner()

		// then
		assertThat(actual).isEqualTo(Marker.X)
	}
	@Test
	fun `남은 좌표를 가져오면, 남은 좌표를 반환한다`() {
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
		val actual = board.getRemainPoints()

		// then
		assertThat(actual).isEqualTo(listOf(Point(2, 1), Point(2, 2)))
	}
}