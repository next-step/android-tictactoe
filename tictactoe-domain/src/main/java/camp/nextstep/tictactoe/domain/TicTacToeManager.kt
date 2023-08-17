package camp.nextstep.tictactoe.domain

class TicTacToeManager(initMode: Mode) {

	private var mode: Mode = initMode
	private var currentPlayer: Player = mode.getFirst()

	fun mark(point: Point, board: Board): Board {
		val newBoard = board.set(point, currentPlayer.marker)
		currentPlayer = mode.getNext(currentPlayer)

		return newBoard
	}
}