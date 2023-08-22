package camp.nextstep.tictactoe.domain.usecase

import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.Point

class GetRandomPointCandidatesUseCase {

	operator fun invoke(board: Board): List<Point> {
		return getAllCandidatePoints(board.size).minus(board.getKeys())
	}

	private fun getAllCandidatePoints(size: Int): List<Point> {
		val candidates = mutableListOf<Point>()

		repeat(size) { x ->
			repeat(size) { y ->
				candidates.add(Point(x, y))
			}
		}

		return candidates
	}
}