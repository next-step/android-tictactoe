package camp.nextstep.tictactoe.domain

data class TicTacToe(
	val mode: Mode,
	val player: Player = mode.getFirst(),
	val board: Board = Board.EMPTY,
) {
	val nextPlayer = mode.getNext(player)

	companion object {
		val INIT = TicTacToe(mode = Mode.Random)

		fun create(mode: Mode) = TicTacToe(mode = mode)
	}
}