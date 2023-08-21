package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.SingleLiveEvent
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.GameResult
import camp.nextstep.edu.tictactoe.domain.TictactoeGame

class TictactoeViewModel(private val tictactoeGame: TictactoeGame) : ViewModel() {

    private val _tictactoeMap = MutableLiveData<HashMap<CellPosition, Boolean>>()
    val tictactoeMap: LiveData<HashMap<CellPosition, Boolean>> = _tictactoeMap

    var isXturn = true

    private val _uiState = SingleLiveEvent<UiMessage>()
    val uiState: LiveData<UiMessage> = _uiState

    init {
        _tictactoeMap.value = HashMap()
    }

    fun clickCell(cellPosition: CellPosition) {
        runCatching {
            when (tictactoeGame.setPosition(isXturn, cellPosition).result) {
                GameResult.GAME_DRAW -> _uiState.value = UiMessage("무승부")
                GameResult.GAME_X_WIN -> _uiState.value = UiMessage("X 승리")
                GameResult.GAME_O_WIN -> _uiState.value = UiMessage("O 승리")
            }
            val data = _tictactoeMap.value
            if (data != null) {
                data[cellPosition] = isXturn
                _tictactoeMap.value = data!!
            }
            isXturn = !isXturn
        }.getOrElse {
            _uiState.value = UiMessage("선택한 곳에 동일하게 선택할 수 없습니다")
        }
    }

    fun getXOResource(cellPosition: CellPosition): Boolean? {
        return tictactoeGame.getCellPosition(cellPosition)
    }

    fun gameReset() {
        isXturn = true
        _tictactoeMap.value = HashMap()
        tictactoeGame.gameReset()
    }
}

data class UiMessage(val message: String = "")
