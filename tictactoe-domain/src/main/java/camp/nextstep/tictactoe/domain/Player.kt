package camp.nextstep.tictactoe.domain

sealed interface Player {
	val marker: Marker

	data class Person(override val marker: Marker = Marker.X) : Player

	data class RandomAi(
		override val marker: Marker = Marker.O,
		val strategy: Strategy = RandomStrategy(),
	) : Player
}