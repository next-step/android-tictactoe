package camp.nextstep.edu.tictactoe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

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
        if(checkClickPositionList[1] == checkClickPositionList[2] && checkClickPositionList[2] == checkClickPositionList[3] ||
            checkClickPositionList[4] == checkClickPositionList[5] && checkClickPositionList[5] == checkClickPositionList[6] ||
            checkClickPositionList[7] == checkClickPositionList[8] && checkClickPositionList[8] == checkClickPositionList[9]) {
            // 가로 승리 조건
            Log.d(TAG, "isWin: ${turn % 2 +1}플레이어 승리")
        } else if(checkClickPositionList[1] == checkClickPositionList[4] && checkClickPositionList[4] == checkClickPositionList[7] ||
            checkClickPositionList[2] == checkClickPositionList[5] && checkClickPositionList[5] == checkClickPositionList[8] ||
            checkClickPositionList[3] == checkClickPositionList[6] && checkClickPositionList[6] == checkClickPositionList[7]) {
            // 세로 승리 조건
            Log.d(TAG, "isWin: ${turn % 2 +1}플레이어 승리")
        } else if(checkClickPositionList[1] == checkClickPositionList[5] && checkClickPositionList[5] == checkClickPositionList[9] ||
            checkClickPositionList[3] == checkClickPositionList[5] && checkClickPositionList[5] == checkClickPositionList[7]) {
            // 대각선 승리 조건
            Log.d(TAG, "isWin: ${turn % 2 +1}플레이어 승리")
        }
    }

    companion object {
        private const val TAG = "TictactoeViewModel"
    }
}
