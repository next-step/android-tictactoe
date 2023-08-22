package camp.nextstep.tictactoe.domain.usecase

import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Point
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GetRandomPointCandidatesUseCaseTest {

	private lateinit var getRandomPointCandidatesUseCase: GetRandomPointCandidatesUseCase

	@Before
	fun setUp() {
		getRandomPointCandidatesUseCase = GetRandomPointCandidatesUseCase()
	}

	@Test
	fun `랜덤 좌표 후보 리스트를 가져오면, Board 에 포함되지 않는 좌표들을 반환한다`() {
		// given
		/**
		 * X O X
		 * O . .
		 * O X .
		 */
		val board = Board(
			map = mapOf(
				Point(0, 0) to Marker.X,
				Point(0, 1) to Marker.O,
				Point(0, 2) to Marker.X,
				Point(1, 0) to Marker.O,
				Point(2, 0) to Marker.O,
				Point(2, 1) to Marker.X,
			)
		)
		val expected = listOf(Point(1, 1), Point(1, 2), Point(2, 2))

		// when
		val actual = getRandomPointCandidatesUseCase(board)

		// then
		assertThat(actual).isEqualTo(expected)
	}
}