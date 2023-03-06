package camp.nextstep.edu.tictactoe

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val game: TicTacToeGame = TicTacToeGame()

    private val _gameStatus = MutableLiveData(game.currentGameStatus)
    val gameStatus: LiveData<TicTacToeStatus>
        get() = _gameStatus

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun reset() {
        game.reset()
        _gameStatus.value = game.currentGameStatus
    }

    fun setGameMode(gameMode: GameMode) {
        game.changeGameMode(gameMode)
        game.reset()
        _gameStatus.value = game.currentGameStatus
    }

    fun putCell(x: Int, y: Int) {
        val position = Position(x, y)
        runCatching {
            game.putCell(position)
        }.onSuccess {
            _gameStatus.value = game.currentGameStatus
        }.onFailure {
            _errorMessage.postValue(it.message)
        }
    }

}