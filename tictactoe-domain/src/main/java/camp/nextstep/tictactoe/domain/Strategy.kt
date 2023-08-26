package camp.nextstep.tictactoe.domain

interface Strategy {
	fun getRandomPoint(ticTacToe: TicTacToe): Point
}

class RandomStrategy: Strategy {
	override fun getRandomPoint(ticTacToe: TicTacToe): Point {
		return ticTacToe.board.getRemainPoints().random()
	}
}