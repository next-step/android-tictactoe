package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.Player
import com.example.tictectoe_domain.PlayerSelectedInfo

class TictactoeViewModel(
   private val game: Game
) : ViewModel() {

    private val _clickBoard = SingleLiveEvent<PlayerSelectedInfo>()
    val clickBoard: LiveData<PlayerSelectedInfo>
        get() = _clickBoard

    private val _clickRestart = SingleLiveEvent<Unit>()
    val clickRestart: LiveData<Unit>
        get() = _clickRestart

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int>
        get() = _toastEvent

    fun clickBoard(position: Int) {
        if (game.canSelect(position)) {
            _clickBoard.value = PlayerSelectedInfo(position, game.getPlayer())
            game.selectBoard(position)
            checkGameWin()
        }
    }

    fun clickRestart() {
        game.startGame()
        _clickRestart.value = Unit
    }

    private fun checkGameWin() {
        when (game.checkGameWin()) {
            Player.PLAYER1 -> {
                _toastEvent.value = R.string.msg_player1_win
            }
            Player.PLAYER2 -> {
                _toastEvent.value = R.string.msg_player2_win
            }
            Player.NONE -> {
                if (game.checkGameDraw()) {
                    _toastEvent.value = R.string.msg_draw
                }
            }
        }
    }
}
