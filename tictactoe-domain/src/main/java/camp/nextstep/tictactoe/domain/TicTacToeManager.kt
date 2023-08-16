package camp.nextstep.tictactoe.domain

class TicTacToeManager {

	fun mark(player: Player, point: Point, board: Board): Board {
		return when (player) {
			is Player.Person -> markByPerson(player, point, board)
		}
	}

	private fun markByPerson(player: Player, point: Point, board: Board): Board {
		return board.set(point, player.marker)
	}
}