package camp.nextstep.tictactoe.domain

sealed class Mode(val player1: Player, val player2: Player) {
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
}
