package camp.nextstep.edu.tictactoe.domain

sealed class GameResult<out T : Any> {
    data class GameStatus<out T : Any>(val result: Int) : GameResult<T>()
    data class Fail(val message: String?) : GameResult<Nothing>()

    companion object {
        const val GAME_ING = 0
        const val GAME_X_WIN = 1
        const val GAME_O_WIN = 2
        const val GAME_DRAW = 3
    }
}
