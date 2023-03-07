package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.Game
import com.example.domain.GameState
import com.example.domain.SelectMode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TicTacToeViewModel @Inject constructor(game: Game) : ViewModel() {

    private val game: Game

    private val _mode = MutableLiveData(SelectMode.Draw)
    val mode: LiveData<SelectMode>
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

    fun changeGameMode(mode: SelectMode) {
        runCatching { game.changeMode(mode) }
            .onSuccess {
                _state.value = game.state
                _mode.value = mode
            }
            .onFailure { _exceptionMessage.value = it.message }
    }
}
