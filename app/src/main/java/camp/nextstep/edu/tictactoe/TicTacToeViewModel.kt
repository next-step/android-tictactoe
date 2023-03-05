package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.*

class TicTacToeViewModel(game: Game = Game()) : ViewModel() {

    private val game: Game

    private val _mode = MutableLiveData("무승부 모드")
    val mode: LiveData<String>
        get() = _mode

    private val _state = MutableLiveData(game.state)
    val state: LiveData<GameState>
        get() = _state


    private val _exceptionMessage = MutableLiveData<String>()
    val exceptionMessage: LiveData<String>
        get() = _exceptionMessage

    init {
        this.game = game
    }

    fun assign(blockIndex: Int) {
        runCatching { game.assignBlock(blockIndex) }
            .onSuccess { _state.value = game.state }
            .onFailure { _exceptionMessage.value = it.message }
    }

    fun reset() {
        game.reset()
        _state.value = game.state
    }

    fun changeTwoPlayerMode() {
        runCatching { game.changePlayerMode() }
            .onSuccess {
                _state.value = game.state
                _mode.value = "2인 모드"
            }
            .onFailure { _exceptionMessage.value = it.message }
    }

    fun changeRandomMode() {
        runCatching { game.changeRandomMode() }
            .onSuccess {
                _state.value = game.state
                _mode.value = "랜덤 모드"
            }
            .onFailure { _exceptionMessage.value = it.message }
    }

    fun changeDrawMode() {
        runCatching { game.changeDrawMode() }
            .onSuccess {
                _state.value = game.state
                _mode.value = "무승부 모드"
            }
            .onFailure { _exceptionMessage.value = it.message }
    }
}
