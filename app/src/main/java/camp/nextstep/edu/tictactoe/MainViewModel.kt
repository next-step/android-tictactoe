package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.GameManager
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe.Companion.BOARD_SIZE
import camp.nextstep.edu.tictactoe.domain.model.Cell
import camp.nextstep.edu.tictactoe.domain.model.GameMode
import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.State
import camp.nextstep.edu.tictactoe.model.TurnResultMessage
import camp.nextstep.edu.tictactoe.model.TurnState

class MainViewModel : ViewModel() {

    private val gameManager = GameManager()
    private val _board = Array(BOARD_SIZE) { Array(BOARD_SIZE) { MutableLiveData(TurnState.EMPTY) } }

    val board: Array<Array<LiveData<TurnState>>>
        get() = _board.map { it.map { liveData -> liveData as LiveData<TurnState> }.toTypedArray() }
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
        for (r in 0 until Board_SIZE) {
            for (c in 0 until Board_SIZE) {
                _board[r][c].value = TurnState.EMPTY
            }
        }
    }

    private fun drawOorXWithPoint(cell: Cell) {
        _board[cell.position.row][cell.position.column].value = TurnState.from(cell)
    }

    companion object {
        const val Board_SIZE = Ticktacktoe.BOARD_SIZE
    }
}