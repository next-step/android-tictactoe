package camp.nextstep.edu.tictactoe

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.*

class TicTacToeViewModel(game: Game = Game()) : ViewModel() {

    private val game: Game

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

    fun changeMode(gameMode: GameMode) {
        runCatching { game.changeMode(gameMode) }
            .onSuccess { _state.value = game.state }
            .onFailure { _exceptionMessage.value = it.message }
    }
}
