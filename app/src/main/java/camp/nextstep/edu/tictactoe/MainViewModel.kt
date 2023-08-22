package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToe
import camp.nextstep.tictactoe.domain.usecase.GetGameStatusUseCase
import camp.nextstep.tictactoe.domain.usecase.MarkBoardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
	private val markBoard: MarkBoardUseCase,
	private val getGameStatus: GetGameStatusUseCase,
) : ViewModel() {

	private val _ticTaeToc: MutableStateFlow<TicTacToe> = MutableStateFlow(TicTacToe.INIT)
	val ticTaeToc: StateFlow<TicTacToe> = _ticTaeToc.asStateFlow()

	private val _gameStatus: MutableStateFlow<GameStatus> = MutableStateFlow(GameStatus.InProgress)
	val gameStatus: StateFlow<GameStatus> = _gameStatus.asStateFlow()

	fun mark(x: Int, y: Int) {
		val ticTaeTocSnapshot = _ticTaeToc.value

		val newTicTaeToc = markBoard(Point(x, y), ticTaeTocSnapshot)
		updateTicTaeToc(newTicTaeToc)
		updateGameStatus(getGameStatus(newTicTaeToc.board))
	}

	private fun updateTicTaeToc(newTicTaeToc: TicTacToe) {
		_ticTaeToc.update { newTicTaeToc }
	}

	private fun updateGameStatus(newGameStatus: GameStatus) {
		_gameStatus.update { newGameStatus }
	}

	fun restartGame() {
		updateTicTaeToc(TicTacToe.INIT)
		updateGameStatus(GameStatus.InProgress)
	}

	fun updateMode(mode: Mode) {
		updateTicTaeToc(
			TicTacToe(
				mode = mode,
				player = mode.getFirst(),
				board = Board.EMPTY
			)
		)
		updateGameStatus(GameStatus.InProgress)
	}
}