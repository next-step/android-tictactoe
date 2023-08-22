package camp.nextstep.tictactoe.domain

data class TicTacToe(
	val mode: Mode = Mode.TwoPerson,
	val player: Player = mode.getFirst(),
	val board: Board = Board.EMPTY,
) {

	fun getNextPlayer(): Player {
		return mode.getNext(player)
	}

	companion object {
		val INIT = TicTacToe(
			mode = Mode.TwoPerson,
			player = Mode.TwoPerson.getFirst(),
			board = Board.EMPTY
		)
	}
}