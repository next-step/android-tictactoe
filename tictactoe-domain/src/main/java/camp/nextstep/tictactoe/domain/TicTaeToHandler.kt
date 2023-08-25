package camp.nextstep.tictactoe.domain

interface TicTaeToHandler {

	fun mark(point: Point, ticTacToe: TicTacToe): TicTacToe
	fun getGameStatus(board: Board): GameStatus
	fun markRandomlyIfNeed(ticTacToe: TicTacToe): TicTacToe
}