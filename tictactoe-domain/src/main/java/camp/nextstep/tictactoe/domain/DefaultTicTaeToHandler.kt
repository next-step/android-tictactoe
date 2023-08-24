package camp.nextstep.tictactoe.domain

class DefaultTicTaeToHandler : TicTaeToHandler {

	override fun mark(point: Point, ticTacToe: TicTacToe): TicTacToe {
		return when (val boardStatus = ticTacToe.board.set(point, ticTacToe.player.marker)) {
			is SetBoardStatus.AlreadyExist -> ticTacToe
			is SetBoardStatus.Success -> ticTacToe.copy(board = boardStatus.board, player = ticTacToe.nextPlayer)
		}
	}

	override fun getGameStatus(board: Board): GameStatus {
		board.getWinner()?.let { winner ->
			return GameStatus.End(winner)
		}

		val remainPoints = board.getRemainPoints()
		return if (remainPoints.isEmpty()) {
			GameStatus.Draw
		} else {
			GameStatus.InProgress
		}
	}

	override fun markRandomlyIfNeed(ticTacToe: TicTacToe): TicTacToe {
		return if (ticTacToe.player is Player.RandomAi) {
			val randomPoint = ticTacToe.board.getRemainPoints().random()
			mark(randomPoint, ticTacToe)
		} else {
			ticTacToe
		}
	}
}