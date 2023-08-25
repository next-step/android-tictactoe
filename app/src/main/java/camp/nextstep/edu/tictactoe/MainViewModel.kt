package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToe
import camp.nextstep.tictactoe.domain.TicTaeToHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
	private val ticTaeToHandler: TicTaeToHandler,
) : ViewModel() {

	private val _ticTacToe: MutableStateFlow<TicTacToe> = MutableStateFlow(TicTacToe.INIT)
	val ticTacToe: StateFlow<TicTacToe> = _ticTacToe.asStateFlow()

	private val _gameStatus: MutableStateFlow<GameStatus> = MutableStateFlow(GameStatus.InProgress)
	val gameStatus: StateFlow<GameStatus> = _gameStatus.asStateFlow()

	fun mark(x: Int, y: Int) {
		val ticTacToeSnapshot = _ticTacToe.value

		val newTicTacToe = ticTaeToHandler.mark(Point(x, y), ticTacToeSnapshot)
		updateTicTaeToe(newTicTacToe)

		val newGameStatus = ticTaeToHandler.getGameStatus(newTicTacToe.board)
		updateGameStatus(newGameStatus)

		if (newGameStatus is GameStatus.InProgress) {
			markRandomlyIfNeed()
		}
	}

	private fun markRandomlyIfNeed() {
		val ticTacToeSnapshot = _ticTacToe.value

		val newTicTacToe = ticTaeToHandler.markRandomlyIfNeed(ticTacToeSnapshot)
		updateTicTaeToe(newTicTacToe)

		val newGameStatus = ticTaeToHandler.getGameStatus(newTicTacToe.board)
		updateGameStatus(newGameStatus)
	}

	private fun updateTicTaeToe(newTicTacToe: TicTacToe) {
		_ticTacToe.update { newTicTacToe }
	}

	fun restartGame() {
		updateTicTaeToe(TicTacToe.INIT)
		updateGameStatus(GameStatus.InProgress)
	}

	fun updateMode(mode: Mode) {
		updateTicTaeToe(TicTacToe.create(mode = mode))
		updateGameStatus(GameStatus.InProgress)
	}

	private fun updateGameStatus(newGameStatus: GameStatus) {
		_gameStatus.update { newGameStatus }
	}
}