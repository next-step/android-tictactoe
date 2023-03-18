package camp.nextstep.edu.tictactoe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.GameManager
import camp.nextstep.edu.tictactoe.domain.model.*
import camp.nextstep.edu.tictactoe.model.TurnResultMessage

class MainViewModel : ViewModel() {

    private val gameManager = GameManager()
    private val _board = MutableLiveData(Board.EMPTY)

    val board: LiveData<Board>
        get() = _board

    private val _showToast = MutableLiveData<TurnResultMessage>()
    val showToast: LiveData<TurnResultMessage>
        get() = _showToast

    fun put(position: Position) {

        runCatching {
            gameManager.runOneTurn(position)
        }.onSuccess { result ->
            _board.value = result.board
            when (result.state) {
                State.WinX -> _showToast.value = TurnResultMessage.GameResultMessage.XWin
                State.WinO -> _showToast.value = TurnResultMessage.GameResultMessage.OWin
                State.Draw -> _showToast.value = TurnResultMessage.GameResultMessage.Tie
                else -> {}
            }
        }.onFailure { throwable ->
            when (throwable as? TurnError) {
                is TurnError.DuplicatedInput -> _showToast.value =
                    TurnResultMessage.ErrorMessage.WrongClick
                is TurnError.GameFinish -> _showToast.value =
                    TurnResultMessage.ErrorMessage.FinishGame
                null -> {
                    Log.e("MainViewModel", "일치하는 에러가 없습니다.")
                }
            }
        }

    }

    fun changeMode(mode: GameMode) {
        gameManager.changeMode(mode)
        reset()
    }

    fun reset() {
        resetPoint()
        gameManager.reset()
    }

    private fun resetPoint() {
        _board.value = Board.EMPTY
    }

}
