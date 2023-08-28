package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tictectoe_domain.TictectoeBoard
import com.example.tictectoe_domain.TictectoeRule

class TictactoeViewModelFactory(
    private val gameBoard: TictectoeBoard,
    private val gameRule: TictectoeRule
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TictactoeViewModel::class.java)) {
            return TictactoeViewModel(gameBoard, gameRule) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
