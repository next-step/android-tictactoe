package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.SingleLiveEvent
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import camp.nextstep.edu.tictactoe.domain.TictactoeMap
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
import camp.nextstep.edu.tictactoe.domain.strategy.RandomStrategy
import camp.nextstep.edu.tictactoe.mode.Mode

class TictactoeViewModel : ViewModel() {
    private var tictactoeGame: TictactoeGame = TictactoeGame(RandomStrategy())

    private val _tictactoeMap = MutableLiveData(tictactoeGame.tictactoeMap)
    val tictactoeMap: LiveData<TictactoeMap> = _tictactoeMap

    private val _uiState = SingleLiveEvent<GameResultUiState>()
    val uiState: LiveData<GameResultUiState> = _uiState

    fun clickCell(cellPosition: CellPosition) {
        runCatching {
            val result = tictactoeGame.mark(cellPosition)
            updateUiStatus(result)
        }.getOrElse {
            _uiState.value = GameResultUiState.Fail(it.message ?: "")
        }
    }

    fun changeMode(mode: Mode) {
        tictactoeGame = when (mode) {
            Mode.DEFAULT -> TictactoeGame()
            Mode.RANDOM -> TictactoeGame(RandomStrategy())
        }
        updateTictactoeMap()
    }

    fun doStrategy() {
        val result = tictactoeGame.markByStrategy()
        if (result != null) {
            updateUiStatus(result)
        }
    }

    fun gameReset() {
        tictactoeGame.gameReset()
        updateTictactoeMap()
    }

    private fun updateUiStatus(result: TictactoeStatus) {
        _uiState.value = GameResultUiState.Status(result)
        updateTictactoeMap()
    }

    private fun updateTictactoeMap() {
        _tictactoeMap.value = tictactoeGame.tictactoeMap
    }
}

sealed class GameResultUiState {
    data class Status(val status: TictactoeStatus) : GameResultUiState()
    data class Fail(val message: String) : GameResultUiState()
}
