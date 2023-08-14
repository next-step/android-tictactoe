package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.model.TictactocCell
import camp.nextstep.edu.tictactoe.utils.Event
import com.nextstep.edu.tictactoe.domain.GameResultManager
import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Turn

class TictactocViewModel : ViewModel() {

    private val tictactoe = Tictactoe(Turn.X, GameResultManager())

    private val _tictactoeBoard = Array(MAP_SIZE) { Array(MAP_SIZE) { MutableLiveData<Turn>(Turn.UNKNOWN) } }

    val tictactoeBoard: Array<Array<LiveData<Turn>>> =
        _tictactoeBoard.map { rowArray ->
            rowArray.map { mutableLiveData ->
                mutableLiveData as LiveData<Turn>
            }.toTypedArray()
        }.toTypedArray()

    private val _tictactocToastMessage: MutableLiveData<Event<TictactocToastMessage>> = MutableLiveData()
    val tictactocToastMessage: LiveData<Event<TictactocToastMessage>> = _tictactocToastMessage

    fun onSetBoardPoint(tictactocCell: TictactocCell) {
        val point = TictactocCell.toPoint(tictactocCell)

        val (gameResult, gameState) = tictactoe.put(point)
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
                _tictactoeBoard[point.row][point.column].value = gameState.turn
                isFinishGame(gameResult = gameResult)
            }
        }
    }

    fun onRestBoard() {
        tictactoe.reset()
        for (row in 0 until MAP_SIZE) {
            for (column in 0 until MAP_SIZE) {
                _tictactoeBoard[row][column].value = Turn.UNKNOWN
            }
        }
    }

    private fun isFinishGame(gameResult: GameResult) {
        when (gameResult) {
            GameResult.X_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.XWin)
            GameResult.O_WIN -> _tictactocToastMessage.value = Event(TictactocToastMessage.OWin)
            GameResult.TIE -> _tictactocToastMessage.value = Event(TictactocToastMessage.Tie)
            else -> {}
        }
    }

    companion object {
        const val MAP_SIZE = Tictactoe.MAP_SIZE
    }
}