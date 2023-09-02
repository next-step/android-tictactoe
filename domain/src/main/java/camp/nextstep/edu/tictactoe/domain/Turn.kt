/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain


enum class Turn {
    X, O;

    companion object {
        fun change(turn: Turn): Turn {
            return when (turn) {
                X -> O
                O -> X
            }
        }
    }
}
