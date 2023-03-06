package camp.nextstep.edu.tictactoe

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val game: TicTacToeGame = TicTacToeGame()
    private val board: TicTacToeBoard = game.board

    private val _gameStatus = MutableLiveData(game.getCurrentGameState())
    val gameStatus: LiveData<TicTacToeStatus>
        get() = _gameStatus

    private val _errorMessage = SingleLiveEvent<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _cells: MutableLiveData<List<List<OX?>>> = MutableLiveData()
    val cells: LiveData<List<List<OX?>>> get() = _cells

    fun reset() {
        game.reset()
        gameStateUpdate()
    }

    fun setGameMode(gameMode: GameMode) {
        game.changeGameMode(gameMode)
        game.reset()
        gameStateUpdate()
    }

    fun putCell(x: Int, y: Int) {
        val position = Position(x, y)

        runCatching {
            game.putCell(position)
        }.onSuccess {
            gameStateUpdate()
        }.onFailure {
            _errorMessage.postValue(it.message)
        }
    }

    private fun gameStateUpdate() {
        _cells.value = board.getAllCell()
        _gameStatus.value = game.getCurrentGameState()
    }

}