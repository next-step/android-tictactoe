package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.GameManager
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe
import camp.nextstep.edu.tictactoe.domain.model.Cell
import camp.nextstep.edu.tictactoe.domain.model.GameMode
import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.State
import camp.nextstep.edu.tictactoe.model.TurnResultMessage
import camp.nextstep.edu.tictactoe.model.TurnState

class MainViewModel : ViewModel() {

    private val gameManager = GameManager()
    private val _map = Array(MAP_SIZE) { Array(MAP_SIZE) { MutableLiveData(TurnState.EMPTY) } }

    val map: Array<Array<LiveData<TurnState>>>
        get() = _map.map { it.map { liveData -> liveData as LiveData<TurnState> }.toTypedArray() }
            .toTypedArray()

    private val _showToast = MutableLiveData<TurnResultMessage>()
    val showToast: LiveData<TurnResultMessage>
        get() = _showToast

    fun put(position: Position) {
        if (gameManager.isNotValidData(position)) {
            if (gameManager.isFinish()) {
                _showToast.value = TurnResultMessage.ErrorMessage.FinishGame
            } else {
                _showToast.value = TurnResultMessage.ErrorMessage.WrongClick
            }
            return
        }

        var result = gameManager.runOneTurn(position)

        result.cells.forEach {
            drawOorXWithPoint(it)
        }


        when (result.state) {
            State.WinX -> _showToast.value = TurnResultMessage.GameResultMessage.XWin
            State.WinO -> _showToast.value = TurnResultMessage.GameResultMessage.OWin
            State.Draw -> _showToast.value = TurnResultMessage.GameResultMessage.Tie
            else -> {}
        }
    }

    fun changeMode(mode: GameMode) {
        gameManager.changeMode(mode)
    }

    fun reset() {
        resetPoint()
        gameManager.reset()
    }

    private fun resetPoint() {
        for (r in 0 until MAP_SIZE) {
            for (c in 0 until MAP_SIZE) {
                _map[r][c].value = TurnState.EMPTY
            }
        }
    }

    private fun drawOorXWithPoint(cell: Cell) {
        _map[cell.position.row][cell.position.column].value = TurnState.from(cell)
    }

    companion object {
        const val MAP_SIZE = Ticktacktoe.MAP_SIZE
    }
}