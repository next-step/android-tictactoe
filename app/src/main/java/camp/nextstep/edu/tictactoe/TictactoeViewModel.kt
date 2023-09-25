package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictectoe_domain.Board
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.GameMode
import com.example.tictectoe_domain.GameStatus
import com.example.tictectoe_domain.Position
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TictactoeViewModel @Inject constructor(
    private val game: Game
) : ViewModel() {

    private val _board = MutableLiveData(game.getBoard())
    val board: LiveData<Board>
        get() = _board

    private var _gameStatus = MutableLiveData(game.gameStatus)
    val gameStatus: LiveData<GameStatus>
        get() = _gameStatus

    fun clickBoard(position: Position) {
        if (!game.isPlay()) {
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
