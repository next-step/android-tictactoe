package camp.nextstep.tictactoe.domain.usecase

import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.SetBoardStatus
import camp.nextstep.tictactoe.domain.TicTacToe

class MarkBoardUseCase {

	operator fun invoke(point: Point, ticTacToe: TicTacToe): TicTacToe {
		return when (val setBoardStatus = ticTacToe.board.set(point, ticTacToe.player.marker)) {
			is SetBoardStatus.Success -> {
				ticTacToe.copy(
					player = ticTacToe.getNextPlayer(),
					board = setBoardStatus.board
				)
			}

			is SetBoardStatus.AlreadyExist -> {
				ticTacToe
			}
		}
	}
}