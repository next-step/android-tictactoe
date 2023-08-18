package camp.nextstep.tictactoe.domain

class TicTacToeManager(initMode: Mode) {

	private var mode: Mode = initMode
	private var currentPlayer: Player = mode.getFirst()

	fun mark(point: Point, board: Board): Board? {
		val newBoard = board.set(point, currentPlayer.marker) ?: return null
		currentPlayer = mode.getNext(currentPlayer)

		return newBoard
	}

	fun getGameStatus(board: Board): GameStatus {
		getWinner(board)?.let { winnerMarker ->
			return GameStatus.End(winnerMarker)
		}

		return if (isDraw(board)) {
			GameStatus.Draw
		} else {
			GameStatus.InProgress
		}
	}

	private fun getWinner(board: Board): Marker? {
		// 행
		for (row in 0 until board.size) {
			getWinner(board) { point -> point.x == row }?.let { return it }
		}

		// 열
		for (col in 0 until board.size) {
			getWinner(board) { point -> point.y == col }?.let { return it }
		}

		// 왼쪽 -> 오른쪽 대각선
		getWinner(board) { point -> point.x == point.y }?.let { return it }

		// 오른쪽 -> 왼쪽 대각선
		getWinner(board) { point -> point.x + point.y == board.size - 1 }?.let { return it }

		return null
	}


	private fun getWinner(board: Board, condition: (Point) -> Boolean): Marker? {
		return when {
			hasWinner(board) { (point, marker) -> condition(point) && marker == Marker.X } -> Marker.X
			hasWinner(board) { (point, marker) -> condition(point) && marker == Marker.O } -> Marker.O
			else -> null
		}
	}

	private fun hasWinner(
		board: Board,
		predicate: (Map.Entry<Point, Marker>) -> Boolean,
	): Boolean {
		val count = board.filter(predicate).size
		return count == board.size
	}

	private fun isDraw(board: Board): Boolean {
		return board.totalMarkerCount == board.size * board.size
	}

	fun restart() {
		currentPlayer = mode.getFirst()
	}
}
