package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tictectoe_domain.Game

class TictactoeViewModelFactory(
    private val game: Game
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TictactoeViewModel::class.java)) {
            return TictactoeViewModel(game) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
