package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.model.Board
import camp.nextstep.edu.tictactoe.model.TictactocCell
import camp.nextstep.edu.tictactoe.utils.Event
import com.nextstep.edu.tictactoe.domain.model.GameMode
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.usecase.GetTictactoeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TictactocViewModel @Inject constructor(
    private val getTictactoeModeUseCase: GetTictactoeModeUseCase
) : ViewModel() {

    private var tictactoe = getTictactoeModeUseCase(gameMode = GameMode.RANDOM_MIDDLE)

    private val _tictactocToastMessage: MutableLiveData<Event<TictactocToastMessage>> = MutableLiveData()
    val tictactocToastMessage: LiveData<Event<TictactocToastMessage>> = _tictactocToastMessage

    private val _tictactocBoard: MutableLiveData<Board> = MutableLiveData(Board.Empty)
    val tictactocBoard: LiveData<Board> = _tictactocBoard

    fun onSetGameMode(gameMode: GameMode) {
        tictactoe = when (gameMode) {
            GameMode.TWO_PLAYER -> getTictactoeModeUseCase(gameMode = GameMode.TWO_PLAYER)
            GameMode.RANDOM -> getTictactoeModeUseCase(gameMode = GameMode.RANDOM, )
            GameMode.RANDOM_MIDDLE -> getTictactoeModeUseCase(gameMode = GameMode.RANDOM_MIDDLE)
        }
        onRestBoard()
    }

    fun onSetBoardPoint(tictactocCell: TictactocCell) {
        val point = TictactocCell.toPoint(tictactocCell)

        val gameResult = tictactoe.put(point)
        when (gameResult) {
            GameResult.FINISH_GAME -> {
                _tictactocToastMessage.value = Event(TictactocToastMessage.GameOver)
                return
            }
            GameResult.INVALID_POSITION -> {
                _tictactocToastMessage.value = Event(TictactocToastMessage.WrongClick)
                return
            }
            else -> {
                tictactoe.changeTurn()
                setBoardFromMap()
                gameResultToastEvent(gameResult = gameResult)
            }
        }
    }

    fun onRestBoard() {
        tictactoe.reset()
        setBoardFromMap()
    }

    private fun gameResultToastEvent(gameResult: GameResult) {
        when (gameResult) {
            GameResult.X_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.XWin)
            GameResult.O_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.OWin)
            GameResult.TIE -> _tictactocToastMessage.value = Event(TictactocToastMessage.Tie)
            else -> {}
        }
    }

    private fun setBoardFromMap() {
        _tictactocBoard.value = Board(tictactoe.getMap())
    }
}