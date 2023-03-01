package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.Winner

class TictactoeViewModel(val tictactoe: Tictactoe = Tictactoe()) : ViewModel() {
    private val _board: MutableList<MutableLiveData<Boolean?>> =
        MutableList(BOARD_SIZE) { MutableLiveData<Boolean?>(null) }
    val board: List<LiveData<Boolean?>>
        get() = _board.toList()
    private val _onResult: SingleLiveEvent<Winner> = SingleLiveEvent()
    val onResult: LiveData<Winner>
        get() = _onResult

    fun mark(position: Int) {
        if (isPlayable()) {
            if (_board[position].value == null) {
                _board[position].value = tictactoe.toggleTurn()
            }

            findWinner()
        }
    }

    private fun findWinner() {
        val board = board.map {
            it.value
        }

        when (tictactoe.findWinner(board)) {
            Winner.X -> _onResult.value = Winner.X
            Winner.O -> _onResult.value = Winner.O
            Winner.DRAW -> _onResult.value = Winner.DRAW
            else -> _onResult.value = Winner.NONE
        }
    }

    private fun isPlayable(): Boolean {
        return _onResult.value == null || _onResult.value == Winner.NONE
    }

    fun restart() {
        _board.forEach {
            it.value = null
        }
        _onResult.value = Winner.NONE
        tictactoe.restart()
    }

    companion object {
        private const val BOARD_SIZE = 9
    }
}
