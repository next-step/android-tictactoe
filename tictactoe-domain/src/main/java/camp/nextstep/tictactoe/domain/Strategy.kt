package camp.nextstep.tictactoe.domain

interface Strategy {
	fun getPoint(ticTacToe: TicTacToe): Point
}

class RandomStrategy : Strategy {
	override fun getPoint(ticTacToe: TicTacToe): Point {
		return ticTacToe.board.getRemainPoints().random()
	}
}

class IntermediateStrategy : Strategy {
	override fun getPoint(ticTacToe: TicTacToe): Point {
		val oOneRemainPoints = ticTacToe.board.getOneRemainPoints(Marker.O)

		return if (oOneRemainPoints.isEmpty()) {
			val xOneRemainPoints = ticTacToe.board.getOneRemainPoints(Marker.X)

			if (xOneRemainPoints.isEmpty()) {
				ticTacToe.board.getRemainPoints().random()
			} else {
				xOneRemainPoints.random()
			}
		} else {
			oOneRemainPoints.random()
		}
	}
}