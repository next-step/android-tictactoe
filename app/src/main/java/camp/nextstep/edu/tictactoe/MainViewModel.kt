package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Marker
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.Player
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

	private val _gameStatus: MutableStateFlow<GameStatus> = MutableStateFlow(GameStatus.InProgress(TicTacToe.INIT, Player.Person(Marker.X)))
	val gameStatus: StateFlow<GameStatus> = _gameStatus.asStateFlow()

	fun mark(x: Int, y: Int) {
		val gameStatusSnapshot = _gameStatus.value
		if (gameStatusSnapshot !is GameStatus.InProgress) return

		val gameStatus = gameStatusSnapshot.ticTacToe.mark(Point(x, y), gameStatusSnapshot.nextPlayer)
		updateGameStatus(gameStatus)
	}

	fun restartGame() {
		updateGameStatus(GameStatus.InProgress(TicTacToe.INIT, Player.Person(Marker.X)))
	}

	fun updateMode(mode: Mode) {
		updateGameStatus(GameStatus.InProgress(TicTacToe.create(mode), Player.Person(Marker.X)))
	}

	private fun updateGameStatus(newGameStatus: GameStatus) {
		_gameStatus.update { newGameStatus }
	}
}