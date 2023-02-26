package camp.nextstep.edu.tictactoe


// 게임의 진행 상태
internal class GameStatus(private val board : TicTacToeBoard) {

    val currentGameStatus: TicTacToeStatus
        get() = getGameStatus()

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

    private fun getGameStatus(): TicTacToeStatus {
        val winner = Winning.getWinner(board)
        return when {
            isDraw(winner == null) -> TicTacToeStatus.DRAW
            winner == OX.X -> TicTacToeStatus.X_WIN
            winner == OX.O -> TicTacToeStatus.O_WIN
            else -> TicTacToeStatus.PLAYING
        }
    }
}