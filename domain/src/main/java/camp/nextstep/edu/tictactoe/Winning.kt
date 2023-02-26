package camp.nextstep.edu.tictactoe

// 게임의 승리 조건
internal object Winning {

    fun getWinner(board: TicTacToeBoard): OX? {
        val isRowWin = board.isRow()
        val isColumnWin = board.isColumn()
        val isDiagonal = board.isDiagonal()
        val isReverseDiagonal = board.isReverseDiagonal()

        return when {
            isRowWin != null -> isRowWin
            isColumnWin != null -> isColumnWin
            isDiagonal != null -> isDiagonal
            isReverseDiagonal != null -> isReverseDiagonal
            else -> null
        }
    }
}