package camp.nextstep.tictactoe.domain

sealed interface GameStatus {
	object InProgress : GameStatus
	data class End(val winnerMarker: Marker) : GameStatus
	object Draw : GameStatus
}