package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Marker
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

	private val _showWinner: MutableLiveData<Marker> = SingleLiveEvent()
	val showWinner: LiveData<Marker>
		get() = _showWinner

	private val _showDraw: MutableLiveData<Unit> = SingleLiveEvent()
	val showDraw: LiveData<Unit>
		get() = _showDraw

	fun mark(point: Point) {
		val boardSnapshot = _board.value

		val newBoard = ticTacToeManager.mark(point = point, board = boardSnapshot)
		updateBoard(newBoard)

		sendUiEventIfNeed(newBoard)
	}

	private fun sendUiEventIfNeed(newBoard: Board) {
		when (val gameStatus = ticTacToeManager.getGameStatus(newBoard)) {
			is GameStatus.End -> {
				_showWinner.value = gameStatus.winnerMarker
				updateBoard(Board.EMPTY)
			}

			is GameStatus.Draw -> {
				_showDraw.value = Unit
				updateBoard(Board.EMPTY)
			}

			is GameStatus.InProgress -> {
				Unit
			}
		}
	}

	private fun updateBoard(newBoard: Board) {
		_board.update {
			newBoard
		}
	}

	private fun clearBoard() {
		updateBoard(Board.EMPTY)
	}
}