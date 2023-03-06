package camp.nextstep.edu.tictactoe

// 게임의 턴
enum class OX {
    O, X;

    fun change(): OX {
        return when (valueOf(name)) {
            O -> X
            X -> O
        }
    }

    companion object {
        val initTurn = X
    }
}
