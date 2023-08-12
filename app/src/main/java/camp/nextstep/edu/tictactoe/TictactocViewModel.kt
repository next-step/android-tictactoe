package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.utils.Event
import com.nextstep.edu.tictactoe.domain.GameResultManager
import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
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

    private val _tictactocToastMessage: MutableLiveData<TictactocToastMessage> = MutableLiveData()
    val tictactocToastMessage: LiveData<TictactocToastMessage> = _tictactocToastMessage

    fun onSetBoardPoint(point: Point) {
        if (isInValidBoard(point)) return

        val (gameResult, gameState) = tictactoe.put(point)
        tictactoe.changeTurn()

        _tictactoeBoard[point.row][point.column].value = gameState.turn
        isFinishGame(gameResult = gameResult)
    }

    fun onRestBoard() {
        tictactoe.reset()
        for (row in 0 until MAP_SIZE) {
            for (column in 0 until MAP_SIZE) {
                _tictactoeBoard[row][column].value = Turn.UNKNOWN
            }
        }
    }

    private fun isInValidBoard(point: Point): Boolean {
        if (!tictactoe.isValidData(point)) {
            if (tictactoe.isFinish) {
                _tictactocToastMessage.value = TictactocToastMessage.GameOver
            } else {
                _tictactocToastMessage.value = TictactocToastMessage.WrongClick
            }
            return true
        }
        return false
    }

    private fun isFinishGame(gameResult: GameResult) {
        when (gameResult) {
            GameResult.X_WIN -> _tictactocToastMessage.value = TictactocToastMessage.XWin
            GameResult.O_WIN -> _tictactocToastMessage.value = TictactocToastMessage.OWin
            GameResult.TIE -> _tictactocToastMessage.value = TictactocToastMessage.Tie
            else -> {}
        }
    }

    companion object {
        const val MAP_SIZE = Tictactoe.MAP_SIZE
    }
}