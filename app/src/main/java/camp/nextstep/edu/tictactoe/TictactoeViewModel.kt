package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nextstep.edu.tictactoe.domain.Tictactoe

class TictactoeViewModel : ViewModel() {
    private val tictactoe: Tictactoe = Tictactoe()
    private val _board: MutableList<MutableLiveData<Boolean?>> =
        MutableList(BOARD_SIZE) { MutableLiveData<Boolean?>(null) }
    val board: List<LiveData<Boolean?>>
        get() = _board.toList()

    fun mark(position: Int) {
        if (_board[position].value == null) {
            _board[position].value = tictactoe.isXTurn()
        }
    }

    fun restart() {
        _board.forEach {
            it.value = null
        }
        tictactoe.restart()
    }

    companion object {
        private const val BOARD_SIZE = 9
    }
}