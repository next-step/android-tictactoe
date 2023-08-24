package camp.nextstep.tictactoe.domain

data class TicTacToe(
	val mode: Mode,
	val player: Player = mode.getFirst(),
	val board: Board = Board.EMPTY,
) {

	fun mark(point: Point, board: Board = this.board, player: Player): GameStatus {
		return when (val boardStatus = board.set(point, player.marker)) {
			is SetBoardStatus.AlreadyExist -> GameStatus.InProgress(this, player)
			is SetBoardStatus.Success -> getGameStatus(boardStatus.board, player)
		}
	}

	private fun getGameStatus(newBoard: Board, player: Player): GameStatus {
		newBoard.getWinner()?.let { winner ->
			return GameStatus.End(this.copy(board = newBoard), winner)
		}

		val remainPoints = newBoard.getRemainPoints()
		if (remainPoints.isEmpty()) {
			return GameStatus.Draw(this.copy(board = newBoard))
		}

		return when (player) {
			is Player.Person -> {
				when (mode) {
					Mode.TwoPerson -> {
						GameStatus.InProgress(
							this.copy(board = newBoard),
							mode.getNext(player)
						)
					}

					Mode.Random -> {
						val randomPoint = remainPoints.random()
						mark(randomPoint, newBoard, Player.RandomAi())
					}
				}
			}

			is Player.RandomAi -> {
				GameStatus.InProgress(
					this.copy(board = newBoard),
					mode.getNext(player)
				)
			}
		}
	}

	companion object {
		val INIT = TicTacToe(mode = Mode.Random)

		fun create(mode: Mode) = TicTacToe(mode = mode)
	}
}