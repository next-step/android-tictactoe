package camp.nextstep.tictactoe.domain

sealed interface Player {
	val marker: Marker

	data class Person(override val marker: Marker) : Player
	data class Ai(override val marker: Marker, val level: Level): Player
}

enum class Level {
	Random
}