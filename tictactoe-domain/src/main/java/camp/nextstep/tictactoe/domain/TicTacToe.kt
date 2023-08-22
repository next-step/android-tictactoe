package camp.nextstep.tictactoe.domain

data class TicTacToe(
	val mode: Mode = Mode.Random,
	val player: Player = mode.getFirst(),
	val board: Board = Board.EMPTY,
) {

	fun getNextPlayer(): Player {
		return mode.getNext(player)
	}

	companion object {
		val INIT = TicTacToe(
			mode = Mode.Random,
			player = Mode.Random.getFirst(),
			board = Board.EMPTY
		)
	}
}