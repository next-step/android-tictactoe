/**
 * @author Daewon on 31,August,2023
 *
 */

package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.GameStatus
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.Turn
import camp.nextstep.edu.tictactoe.domain.manager.TicTacToeManager
import camp.nextstep.edu.tictactoe.domain.tictactoe.TicTacToe


class TicTacToeViewModel(
    private val manager: TicTacToeManager,
    private val ticTacToe: TicTacToe,
) : ViewModel() {

    private val _uiState = MutableLiveData(UiState(GameStatus.InProgress, Board.EMPTY, Turn.X))
    val uiState: LiveData<UiState> = _uiState

    private val _uiEffect = SingleLiveEvent<UiEffect>()
    val uiEffect: LiveData<UiEffect> = _uiEffect

    fun onClickMark(position: Position) {
        ticTacToe.mark(position)
            .onSuccess {
                checkGameStatus(ticTacToe.getBoard())
            }
            .onFailure {
                _uiEffect.value = UiEffect.ShowToast(it.message ?: "알 수 없는 오류가 발생했습니다.")
            }
    }

    private fun checkGameStatus(board: Board) {
        when (manager.checkGameStatus(board)) {
            GameStatus.InProgress -> {
                _uiState.value = UiState(GameStatus.InProgress, board, ticTacToe.currentTurn())
            }

            GameStatus.Draw -> {
                _uiState.value = UiState(GameStatus.Draw, board, ticTacToe.currentTurn())
                _uiEffect.value = UiEffect.ShowToast("무승부로 종료되었습니다.")
            }

            GameStatus.WinO -> {
                _uiState.value = UiState(GameStatus.WinO, board, Turn.O)
                _uiEffect.value = UiEffect.ShowToast("O 승리.")
            }

            GameStatus.WinX -> {
                _uiState.value = UiState(GameStatus.WinX, board, Turn.X)
                _uiEffect.value = UiEffect.ShowToast("X 승리.")
            }
        }
    }

    fun onClickRestart() {
        ticTacToe.restart()
        initBoard()
        _uiEffect.value = UiEffect.ShowToast("게임을 다시 시작합니다.")
    }

    fun changeMode(mode: Mode) {
        ticTacToe.changeMode(mode)
        ticTacToe.restart()
        initBoard()
        _uiEffect.value = UiEffect.ShowToast("게임 모드가 변경되었습니다.")
    }

    private fun initBoard() {
        _uiState.value =
            UiState(GameStatus.InProgress, ticTacToe.getBoard(), ticTacToe.currentTurn())
    }

}

data class UiState(
    val status: GameStatus,
    val board: Board,
    val turn: Turn
)

sealed interface UiEffect {
    data class ShowToast(val message: String) : UiEffect
}
