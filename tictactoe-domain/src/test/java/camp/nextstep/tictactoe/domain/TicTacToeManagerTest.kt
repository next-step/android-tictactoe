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
}