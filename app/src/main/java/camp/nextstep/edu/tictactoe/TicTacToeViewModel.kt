package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Game
import com.example.domain.GameState

class TicTacToeViewModel(game: Game = Game()) : ViewModel() {

    private var game: Game

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
        try {
            game.assignBlock(blockIndex)
            _state.value = game.state
        } catch (e: Throwable) {
            _exceptionMessage.value = e.message
        }
    }

    fun reset() {
        game.reset()
        _state.value = game.state
    }
}
