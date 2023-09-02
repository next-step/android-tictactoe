/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe.domain


sealed interface GameStatus {
    object InProgress : GameStatus

    sealed interface Ended : GameStatus
    object Draw : Ended
    object WinX : Ended
    object WinO : Ended
}
