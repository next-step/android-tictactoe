package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.GameResultManager
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe
import camp.nextstep.edu.tictactoe.domain.model.GameResult
import camp.nextstep.edu.tictactoe.domain.model.Point
import camp.nextstep.edu.tictactoe.domain.model.Turn
import camp.nextstep.edu.tictactoe.model.TurnResultMessage

class MainViewModel : ViewModel() {

    private val ticktacktoe = Ticktacktoe(Turn.X, GameResultManager())
    private val _map = Array(MAP_SIZE) { Array(MAP_SIZE) { MutableLiveData<Boolean?>(null) } }

    val map: Array<Array<LiveData<Boolean?>>>
        get() = _map.map { it.map { liveData -> liveData as LiveData<Boolean?> }.toTypedArray() }
            .toTypedArray()

    private val _showToast = MutableLiveData<TurnResultMessage>()
    val showToast: LiveData<TurnResultMessage>
        get() = _showToast

    fun put(point: Point) {
        if (!ticktacktoe.isValidData(point)) {
            if (ticktacktoe.isFinish) {
                _showToast.value = TurnResultMessage.ErrorMessage.FinishGame
            } else {
                _showToast.value = TurnResultMessage.ErrorMessage.WrongClick
            }
            return
        }

        var result = ticktacktoe.put(point)
        ticktacktoe.changeTurn()
        drawOorXWithPoint(result.second.point, result.second.turn)


        when (result.first) {
            GameResult.X_WIN -> _showToast.value = TurnResultMessage.GameResultMessage.XWin
            GameResult.O_WIN -> _showToast.value = TurnResultMessage.GameResultMessage.OWin
            GameResult.TIE -> _showToast.value = TurnResultMessage.GameResultMessage.Tie
            else -> {}
        }
    }

    fun reset() {
        resetPoint()
        ticktacktoe.reset()
    }

    private fun resetPoint() {
        for (r in 0 until MAP_SIZE) {
            for (c in 0 until MAP_SIZE) {
                _map[r][c].value = null
            }
        }
    }

    private fun drawOorXWithPoint(point: Point, turn: Turn) {
        _map[point.row][point.column].value = isXTurn(turn)
    }

    private fun isXTurn(turn: Turn): Boolean? {
        return if (turn == Turn.X) true
        else if (turn == Turn.O) false
        else null
    }

    companion object {
        const val MAP_SIZE = Ticktacktoe.MAP_SIZE
    }
}