package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.SingleLiveEvent
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.GameResult
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

class TictactoeViewModel(private val tictactoeGame: TictactoeGame) : ViewModel() {

    private val _tictactoeMap = MutableLiveData<TictactoeMap>()
    val tictactoeMap: LiveData<TictactoeMap> = _tictactoeMap

    private val _uiState = SingleLiveEvent<GameResult<Int>>()
    val uiState: LiveData<GameResult<Int>> = _uiState

    init {
        _tictactoeMap.value = tictactoeGame.tictactoeMap
    }

    fun clickCell(cellPosition: CellPosition) {
        runCatching {
            val result = tictactoeGame.setPosition(cellPosition)
            _uiState.value = result
            _tictactoeMap.value = tictactoeGame.tictactoeMap
        }.getOrElse {
            _uiState.value = GameResult.Fail(it.message)
        }
    }

    fun gameReset() {
        tictactoeGame.gameReset()
        _tictactoeMap.value = tictactoeGame.tictactoeMap
    }
}
