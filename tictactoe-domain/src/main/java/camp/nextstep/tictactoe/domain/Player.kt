package camp.nextstep.tictactoe.domain

sealed interface Player {
	val marker: Marker

	data class Person(override val marker: Marker) : Player
}