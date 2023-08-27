package camp.nextstep.tictactoe.domain

sealed class Mode(private val player1: Player, private val player2: Player) {
	fun getFirst(): Player {
		return player1
	}

	fun getNext(currentPlayer: Player): Player {
		return when (currentPlayer) {
			player1 -> player2
			else -> player1
		}
	}

	object TwoPerson : Mode(Player.Person(Marker.X), Player.Person(Marker.O))
	object Random : Mode(Player.Person(Marker.X), Player.RandomAi(Marker.O, RandomStrategy()))
	object Intermediate: Mode(Player.Person(Marker.X), Player.RandomAi(Marker.O, IntermediateStrategy()))
}
