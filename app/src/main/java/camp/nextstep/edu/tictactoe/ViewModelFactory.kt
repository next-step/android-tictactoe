package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.tictactoe.domain.DefaultTicTaeToHandler

class ViewModelFactory : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return when (modelClass) {
			MainViewModel::class.java -> MainViewModel(DefaultTicTaeToHandler())
			else -> throw IllegalArgumentException()
		} as T
	}
}