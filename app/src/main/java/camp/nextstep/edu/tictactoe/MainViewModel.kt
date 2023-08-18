package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.Point
import camp.nextstep.tictactoe.domain.TicTacToeManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

	private val ticTacToeManager = TicTacToeManager(Mode.TwoPerson)

	private val _board: MutableStateFlow<Board> = MutableStateFlow(Board.EMPTY)
	val board: StateFlow<Board> = _board.asStateFlow()

	private val _gameStatus: MutableLiveData<GameStatus> = MutableLiveData()
	val gameStatus: LiveData<GameStatus>
		get() = _gameStatus

	init {
		_gameStatus.value = GameStatus.InProgress
	}

	fun mark(x: Int, y: Int) {
		val boardSnapshot = _board.value

		val newBoard = ticTacToeManager.mark(point = Point(x, y), board = boardSnapshot) ?: return

		updateBoard(newBoard)
		updateGameStatus(newBoard)
	}

	private fun updateBoard(newBoard: Board) {
		_board.update {
			newBoard
		}
	}

	private fun updateGameStatus(newBoard: Board) {
		_gameStatus.value = ticTacToeManager.getGameStatus(newBoard)
	}

	fun restartGame() {
		_gameStatus.value = GameStatus.InProgress
		updateBoard(Board.EMPTY)
		ticTacToeManager.restart()
	}
}