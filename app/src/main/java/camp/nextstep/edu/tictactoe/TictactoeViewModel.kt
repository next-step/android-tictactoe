package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TictactoeViewModel : ViewModel() {
    private val _isFirst: MutableLiveData<Boolean?> = MutableLiveData(null)
    val isFirst: LiveData<Boolean?> = _isFirst

    private val _board: MutableList<MutableLiveData<Boolean?>> =
        MutableList(BOARD_SIZE) { MutableLiveData<Boolean?>(null) }
    val board: List<LiveData<Boolean?>>
        get() = _board.toList()

    fun mark(position: Int) {
        if (_board[position].value == null) {
            setIsFirstValue()
            _board[position].value = _isFirst.value
        }
    }

    private fun setIsFirstValue() {
        if (_isFirst.value == null) {
            _isFirst.value = true
        } else {
            _isFirst.value = _isFirst.value != true
        }
    }

    fun clear() {
        _board.forEach {
            it.value = null
        }
        _isFirst.value = null
    }

    companion object {
        private const val BOARD_SIZE = 9
    }
}