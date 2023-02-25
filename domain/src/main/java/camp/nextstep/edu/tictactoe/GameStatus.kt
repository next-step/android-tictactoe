package camp.nextstep.edu.tictactoe


internal class GameStatus(private val ticTacToe : Array<Array<OX?>>) {

    val currentGameStatus: TicTacToeStatus
        get() = getGameStatus(ticTacToe)

    // 체크 순서
    var gameTern = OX.O
        private set
        get() {
            putCount++
            field = field.change()
            return field
        }

    // 모든 배열이 체크가 되었는지
    private var putCount = 0

    // 배열이 가득 차거나 게임 승부가 나지 않은 상태
    private fun isDraw(isNotExistedWinner: Boolean): Boolean = isNotExistedWinner && (putCount == 9)

    private fun getGameStatus(ticTacToe : Array<Array<OX?>>): TicTacToeStatus {
        val winner = Winning.getWinner(ticTacToe)
        return when {
            isDraw(winner == null) -> TicTacToeStatus.DRAW
            winner == OX.X -> TicTacToeStatus.X_WIN
            winner == OX.O -> TicTacToeStatus.O_WIN
            else -> TicTacToeStatus.PLAYING
        }
    }
}