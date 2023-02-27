package camp.nextstep.edu.tictactoe

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.*

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

@BindingAdapter("android:assign")
fun setAssign(view: View?, block: Block?) {
    when (block) {
        XBlock -> view?.setBackgroundResource(R.drawable.ic_x_black)
        OBlock -> view?.setBackgroundResource(R.drawable.ic_o_black)
        else -> view?.setBackgroundResource(0)
    }
}
