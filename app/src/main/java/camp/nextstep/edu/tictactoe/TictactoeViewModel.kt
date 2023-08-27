package camp.nextstep.edu.tictactoe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.TictectoeRule

class TictactoeViewModel: ViewModel() {

    private val _clickBoard = SingleLiveEvent<Pair<Int, Int>>()
    val clickBoard: LiveData<Pair<Int, Int>>
        get() = _clickBoard

    private val _clickRestart = SingleLiveEvent<Unit>()
    val clickRestart: LiveData<Unit>
        get() = _clickRestart


    private var turn = 0

    private lateinit var checkClickPositionList: MutableList<Int>

    init {
        clickRestart()
    }

    fun clickBoard(position: Int) {
        if(canClick(position)){
            _clickBoard.postValue(Pair(position, turn % 2))
            checkClickPositionList[position] = turn % 2
            isWin()
            turn++
        }
    }

    fun clickRestart() {
        turn = 0
        checkClickPositionList = MutableList(10){index -> 10 + index}
        _clickRestart.value = Unit
    }

    private fun canClick(position: Int): Boolean {
        return checkClickPositionList[position] >= 10
    }

    private fun isWin() {
        if(TictectoeRule().isWin(checkClickPositionList)) {
            Log.d(TAG, "isWin: ${turn % 2 +1}플레이어 승리")
        }
    }

    companion object {
        private const val TAG = "TictactoeViewModel"
    }
}
