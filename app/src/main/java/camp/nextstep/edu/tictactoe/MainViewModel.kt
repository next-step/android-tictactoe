package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val ticTacToeBoard: TicTacToeBoard = TicTacToeBoard()

    private val _cellsLiveData: MutableLiveData<Array<Array<OX?>>> = MutableLiveData()
    val cellsLiveData: LiveData<Array<Array<OX?>>> get() = _cellsLiveData

    private val _gameStatus = MutableLiveData(ticTacToeBoard.getGameStatus())
    val gameStatus: LiveData<TicTacToeStatus> get() = _gameStatus

    fun reset() {
        ticTacToeBoard.reset()
        _cellsLiveData.value = ticTacToeBoard.getAllCell()
        _gameStatus.value = ticTacToeBoard.getGameStatus()
    }

    fun setGameMode(gameMode: GameMode) {

        reset()
    }

    fun putCell(x: Int, y: Int) {
        if (ticTacToeBoard.getGameStatus() != TicTacToeStatus.PLAYING) return

        ticTacToeBoard.put(x, y)
        _cellsLiveData.value = ticTacToeBoard.getAllCell()
        _gameStatus.value = ticTacToeBoard.getGameStatus()
    }

}