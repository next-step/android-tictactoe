package camp.nextstep.tictactoe.domain

sealed interface GameStatus {
	val ticTacToe: TicTacToe

	data class InProgress(override val ticTacToe: TicTacToe, val nextPlayer: Player) : GameStatus
	data class End(override val ticTacToe: TicTacToe, val winnerMarker: Marker) : GameStatus
	data class Draw(override val ticTacToe: TicTacToe) : GameStatus
}