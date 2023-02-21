package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val ticTacToe: TicTacToe = TicTacToe()

    private val _cellsLiveData: MutableLiveData<TicTacToeType> = MutableLiveData()
    val cellsLiveData: LiveData<TicTacToeType> get() = _cellsLiveData

    private val _gameStatus = MutableLiveData(ticTacToe.currentGameStatus)
    val gameStatus: LiveData<TicTacToeStatus> get() = _gameStatus

    fun reset() {
        ticTacToe.reset()
        _cellsLiveData.value = ticTacToe.getAllCell()
        _gameStatus.value = ticTacToe.currentGameStatus
    }

    fun put(x: Int, y: Int) {
        if (ticTacToe.currentGameStatus != TicTacToeStatus.PLAYING) return

        ticTacToe.put(x, y)
        _cellsLiveData.value = ticTacToe.getAllCell()
        _gameStatus.value = ticTacToe.currentGameStatus
    }

}