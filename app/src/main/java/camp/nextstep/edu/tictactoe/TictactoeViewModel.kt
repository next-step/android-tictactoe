package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.Board
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.GameMode
import com.example.tictectoe_domain.GameStatus
import com.example.tictectoe_domain.Position

class TictactoeViewModel(
   private val game: Game
) : ViewModel() {

    private val _board = MutableLiveData(game.getBoard())
    val board: LiveData<Board>
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

    fun clickBoard(position: Position) {
        // 1. 게임 플레이가 가능한 상태인가?
        if(!game.isPlay()) {
            return
        }

        game.selectBoard(position)

        _board.value = game.getBoard()
        _gameStatus.value = game.checkGameStatus()
    }

    fun clickRestart() {
        game.gameReset()
        _board.value = game.getBoard()
    }

    fun changeGameMode(gameMode: GameMode) {
        clickRestart()
        game.changeGameMode(gameMode)
    }
}
