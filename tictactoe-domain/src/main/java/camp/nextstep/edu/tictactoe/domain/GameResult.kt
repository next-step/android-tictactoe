package camp.nextstep.edu.tictactoe.domain

@JvmInline
value class GameResult(val result : Int) {

    companion object {
        const val GAME_ING = 0
        const val GAME_X_WIN = 1
        const val GAME_O_WIN = 2
        const val GAME_DRAW = 3
    }
}