package camp.nextstep.edu.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.edu.tictactoe.domain.TictactoeGame

class ViewModelFactory() : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            TictactoeViewModel::class.java -> createMainViewModel()
            else -> throw IllegalArgumentException("Cannot find {$modelClass}")
        } as T
    }

    private fun createMainViewModel(): TictactoeViewModel {
        return TictactoeViewModel(TictactoeGame())
    }
}
