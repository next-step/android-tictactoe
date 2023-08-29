package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.SingleLiveEvent
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import camp.nextstep.edu.tictactoe.domain.TictactoeMap
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus

class TictactoeViewModel(private val tictactoeGame: TictactoeGame) : ViewModel() {

    private val _tictactoeMap = MutableLiveData(tictactoeGame.tictactoeMap)
    val tictactoeMap: LiveData<TictactoeMap> = _tictactoeMap

    private val _uiState = SingleLiveEvent<GameResult>()
    val uiState: LiveData<GameResult> = _uiState

    fun clickCell(cellPosition: CellPosition) {
        runCatching {
            val result = tictactoeGame.setPosition(cellPosition)
            _uiState.value = GameResult.Status(result)
            _tictactoeMap.value = tictactoeGame.tictactoeMap
        }.getOrElse {
            _uiState.value = GameResult.Fail(it.message ?: "")
        }
    }

    fun gameReset() {
        tictactoeGame.gameReset()
        _tictactoeMap.value = tictactoeGame.tictactoeMap
    }
}

sealed class GameResult {
    data class Status(val status: TictactoeStatus) : GameResult()
    data class Fail(val message: String) : GameResult()
}
