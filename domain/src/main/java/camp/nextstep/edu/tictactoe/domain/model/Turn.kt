package camp.nextstep.edu.tictactoe.domain.model

enum class Turn {
    X,
    O;

    fun switch(): Turn = when (this) {
        X -> O
        O -> X
    }
}
