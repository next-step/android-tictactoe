package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.SingleLiveEvent
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
import camp.nextstep.edu.tictactoe.domain.strategy.Mode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TictactoeViewModel @Inject constructor(
    private val tictactoeGame: TictactoeGame
) : ViewModel() {

    private val _tictactoeMap = MutableLiveData(tictactoeGame.getMapPositions())
    val tictactoeMap: LiveData<Map<CellPosition, Owner>> = _tictactoeMap

    private val _uiState = SingleLiveEvent<GameResultUiState>()
    val uiState: LiveData<GameResultUiState> = _uiState

    fun clickCell(cellPosition: CellPosition) {
        runCatching {
            val result = tictactoeGame.mark(cellPosition)
            updateUiStatus(result)
            if (result == TictactoeStatus.Progress) {
                continueGame()
            }
        }.getOrElse {
            _uiState.value = GameResultUiState.Fail(it.message ?: "")
        }
    }

    fun changeMode(mode: Mode) {
        tictactoeGame.setMode(mode)
        updateTictactoeMap()
    }

    private fun continueGame() {
        val result = tictactoeGame.markByStrategy()
        if (result != null) {
            updateUiStatus(result)
        }
    }

    fun gameReset() {
        tictactoeGame.resetMap()
        updateTictactoeMap()
    }

    private fun updateUiStatus(result: TictactoeStatus) {
        _uiState.value = GameResultUiState.Status(result)
        updateTictactoeMap()
    }

    private fun updateTictactoeMap() {
        _tictactoeMap.value = tictactoeGame.getMapPositions()
    }
}

sealed class GameResultUiState {
    data class Status(val status: TictactoeStatus) : GameResultUiState()
    data class Fail(val message: String) : GameResultUiState()
}
