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
	fun `key 값이 존재하지 않을 때, 값을 set 하면, 값이 추가된 Board 를 반환한다`() {
		// when
		val actual = board.set(Point(0, 0), Marker.X)

		// then
		assertThat(actual).isEqualTo(Board(map = mapOf(Point(0, 0) to Marker.X)))
	}

	@Test
	fun `key 값이 존재할 때, 값을 set 하면, null 을 반환한다`() {
		// given
		board = Board(map = mapOf(Point(0, 0) to Marker.X))

		// when
		val actual = board.set(Point(0, 0), Marker.O)

		// then
		assertThat(actual).isEqualTo(null)
	}

	@Test
	fun `clear 하면, 빈 값의 Board 를 반환한다`() {
		// given
		board = Board(map = mapOf(Point(0, 0) to Marker.X))

		// when
		val actual = board.clear()

		// then
		assertThat(actual).isEqualTo(Board(map = mapOf()))
	}
}