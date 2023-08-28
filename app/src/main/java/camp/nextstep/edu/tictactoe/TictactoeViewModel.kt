package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.Player
import com.example.tictectoe_domain.TictectoeBoard
import com.example.tictectoe_domain.PlayerSelectedInfo
import com.example.tictectoe_domain.TictectoeRule

class TictactoeViewModel( private val gameBoard: TictectoeBoard,
                          private val gameRule: TictectoeRule) : ViewModel() {

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
        if (canClick(position)) {
            _clickBoard.value = PlayerSelectedInfo(position, gameBoard.getPlayer())
            gameBoard.selectBoard(position)
            gameBoard.changePlayer()
            checkGameWin()
        }
    }

    fun clickRestart() {
        gameBoard.initBoard()
        _clickRestart.value = Unit
    }

    private fun canClick(position: Int): Boolean {
        return gameBoard.canClick(position)
    }

    private fun checkGameWin() {
        val board = gameBoard.getBoard()
        when (gameRule.getWinningPlayer(board)) {
            Player.PLAYER1 -> {
                _toastEvent.value = R.string.msg_player1_win
            }
            Player.PLAYER2 -> {
                _toastEvent.value = R.string.msg_player2_win
            }
            Player.NONE -> {
                checkDraw()
            }
        }
    }

    private fun checkDraw() {
        if(gameRule.isDraw(gameBoard.getBoard())) {
            _toastEvent.value = R.string.msg_draw
        }
    }
}
