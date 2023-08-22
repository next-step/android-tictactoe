package camp.nextstep.tictactoe.domain.usecase

import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Player
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToe
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class MarkBoardUseCaseTest {

	private lateinit var markBoardUseCase: MarkBoardUseCase

	@Before
	fun setUp() {
		markBoardUseCase = MarkBoardUseCase()
	}

	@Test
	fun `존재하지 않는 좌표에 mark 하면, mark 된 Board 와 다음 Player 가 갱신된 TicTaeToe 를 반환한다`() {
		// given
		val ticTacToe = TicTacToe(
			player = Player.Person(Marker.X),
			board = Board(map = mapOf())
		)
		val expected = TicTacToe(
			player = Player.Person(Marker.O),
			board = Board(map = mapOf(Point(0, 0) to Marker.X))
		)

		// when
		val actual = markBoardUseCase(
			point = Point(0, 0),
			ticTacToe = ticTacToe
		)

		// then
		assertThat(actual).isEqualTo(expected)
	}

	@Test
	fun `이미 존재하는 좌표에 mark 하면, 동일한 TicTaeToe 를 반환한다`() {
		// given
		val ticTacToe = TicTacToe(
			player = Player.Person(Marker.X),
			board = Board(map = mapOf(Point(0, 0) to Marker.X))
		)

		// when
		val actual = markBoardUseCase(
			point = Point(0, 0),
			ticTacToe = ticTacToe
		)

		// then
		assertThat(actual).isEqualTo(ticTacToe)
	}
}