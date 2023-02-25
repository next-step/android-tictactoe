package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.GameResult
import camp.nextstep.edu.tictactoe.domain.Point
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe
import camp.nextstep.edu.tictactoe.domain.Turn
import camp.nextstep.edu.tictactoe.model.MessageState

class MainViewModel : ViewModel() {
    private val ticktacktoe = Ticktacktoe()
    private val _point = Array(MAP_SIZE) { Array(MAP_SIZE) { MutableLiveData<Boolean?>(null) } }

    val point: Array<Array<LiveData<Boolean?>>>
        get() = _point.map { it.map { liveData -> liveData as LiveData<Boolean?> }.toTypedArray() }.toTypedArray()

    private val _showToast = MutableLiveData<MessageState>()
    val showToast: LiveData<MessageState>
        get() = _showToast

    fun isWin(point: Point) {

        val result = ticktacktoe.isWin(point)
        if (result == null) {
            if (ticktacktoe.isFinish) {
                _showToast.value = MessageState.FINISH
            } else {
                _showToast.value = MessageState.ERROR
            }
            return
        }
        drawOorXWithPoint(point)
        ticktacktoe.changeTurn()
        when (result) {
            GameResult.X_WIN -> _showToast.value = MessageState.X_WIN
            GameResult.O_WIN -> _showToast.value = MessageState.O_WIN
            GameResult.TIE -> _showToast.value = MessageState.IN_A_TIE
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
                _point[r][c].value = null
            }
        }
    }

    private fun drawOorXWithPoint(point: Point) {

        for (r in 0 until MAP_SIZE) {
            for (c in 0 until MAP_SIZE) {
                if (point == Point(r, c)) _point[r][c].value = isXTurn()
            }
        }
    }

    private fun isXTurn(): Boolean? {
        return if (ticktacktoe.getCurrentTurn() == Turn.X) true
        else if (ticktacktoe.getCurrentTurn() == Turn.O) false
        else null
    }

    companion object {
        const val MAP_SIZE = Ticktacktoe.MAP_SIZE
    }
}