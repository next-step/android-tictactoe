package camp.nextstep.edu.tictactoe

// 게임의 승리 조건
internal object Winning {

    fun getWinner(board: TicTacToeBoard): OX? {
        val isRowWin = board.isRowWin()
        val isColumnWin = board.isColumnWin()
        val isDiagonal = board.isDiagonalWin()
        val isReverseDiagonal = board.isReverseDiagonalWin()

        return when {
            isRowWin != null -> isRowWin
            isColumnWin != null -> isColumnWin
            isDiagonal != null -> isDiagonal
            isReverseDiagonal != null -> isReverseDiagonal
            else -> null
        }
    }
}