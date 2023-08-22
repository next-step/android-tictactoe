package camp.nextstep.edu.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import camp.nextstep.tictactoe.domain.Board
import camp.nextstep.tictactoe.domain.Mode
import camp.nextstep.tictactoe.domain.usecase.GetGameStatusUseCase
import camp.nextstep.tictactoe.domain.usecase.GetRandomPointCandidatesUseCase
import camp.nextstep.tictactoe.domain.usecase.MarkBoardUseCase


class ViewModelFactory : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return when (modelClass) {
			MainViewModel::class.java -> MainViewModel(
				markBoard = MarkBoardUseCase(),
				getGameStatus = GetGameStatusUseCase(),
				getRandomPointCandidates = GetRandomPointCandidatesUseCase()
			)
			else -> throw IllegalArgumentException()
		} as T
	}
}