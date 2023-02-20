package camp.nextstep.edu.tictactoe

enum class OX {
    O, X;

    fun change(): OX {
        return when (valueOf(name)) {
            O -> X
            X -> O
        }
    }
}
