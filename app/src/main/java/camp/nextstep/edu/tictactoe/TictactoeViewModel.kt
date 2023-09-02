package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.Cell
import com.example.tictectoe_domain.GameStatus

class TictactoeViewModel(
   private var game: Game
) : ViewModel() {

    private val _board = MutableLiveData(game.getBoard())
    val board: LiveData<List<Cell>>
        get() = _board

    private var _gameStatus = MutableLiveData(game.gameStatus)
    val gameStatus: LiveData<GameStatus>
        get() = _gameStatus

    private val _clickRestart = SingleLiveEvent<Unit>()
    val clickRestart: LiveData<Unit>
        get() = _clickRestart

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    fun clickBoard(position: Int) {
        // 1. 게임 플레이가 가능한 상태인가?
        if(!game.isPlay()) {
            return
        }

        // 2. cell을 놓을 수 있는 곳인가?
        if(!game.canSelect(position)) {
            return
        }

        game.selectBoard(position)

        _board.value = game.getBoard()
        _gameStatus.value = game.gameStatus
    }

    fun clickRestart() {
        game.gameReset()
        _board.value = game.getBoard()
    }

    fun startTwoPlayerMode() {
        clickRestart()
        game.changeTwoPlayerMode()
    }

    fun startRandomMode() {
        clickRestart()
        game.changeRandomMode()
    }
}
