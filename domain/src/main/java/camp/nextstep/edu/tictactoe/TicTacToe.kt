package camp.nextstep.edu.tictactoe

typealias TicTacToeType = Array<Array<OX?>>

class TicTacToe(
    private var ticTacToe: TicTacToeType = Array(3) { Array(3) { null } }
) {

    val currentGameStatus: TicTacToeStatus
        get() { return getGameStatus() }

    fun put(x: Int, y: Int) {
        if (currentGameStatus != TicTacToeStatus.PLAYING) return

        if (ticTacToe[x][y] == null) {
            ticTacToe[x][y] = isX
            isX = isX.change()
        }
    }

    private fun getGameStatus(): TicTacToeStatus {
        return when {
            isDraw -> TicTacToeStatus.DRAW
            getWinner() == OX.X -> TicTacToeStatus.X_WIN
            getWinner() == OX.O -> TicTacToeStatus.O_WIN
            else -> TicTacToeStatus.PLAYING
        }
    }

    fun getTicTacToeCell(x: Int, y: Int): OX? {
        return ticTacToe[x][y]
    }

    fun getAllCell() = ticTacToe.copyOf()

    fun reset() {
        ticTacToe = newTicTacToe()
    }

    private fun getWinner(): OX? {
        val isRowWin = isRow()
        val isColumnWin = isColumn()
        val isDiagonal = isDiagonal()
        val isReverseDiagonal = isReverseDiagonal()

        return when {
            isRowWin != null -> isRowWin
            isColumnWin != null -> isColumnWin
            isDiagonal != null -> isDiagonal
            isReverseDiagonal != null -> isReverseDiagonal
            else -> null
        }
    }

    private fun isColumn(): OX? {
        for (i in ticTacToe.indices) {
            var answer = true
            val result = ticTacToe[i][i]
            for (j in ticTacToe.indices) {
                answer = answer && ticTacToe[j][i] == result
            }

            if (answer)
                return result
        }

        return null
    }

    private fun isRow(): OX? {
        for (i in ticTacToe.indices) {
            if (ticTacToe[i].all { it == OX.X }) {
                return OX.X
            }

            if (ticTacToe[i].all { it == OX.O }) {
                return OX.O
            }
        }

        return null
    }

    private fun isDiagonal(): OX? {
        var answer = true
        val result = ticTacToe[0][0]
        for (i in ticTacToe.indices) {
            answer = answer && (ticTacToe[i][i] == result)
        }

        return if (answer)
            result
        else
            null
    }

    private fun isReverseDiagonal(): OX? {
        var answer = true
        val result = ticTacToe[0][ticTacToe.size - 1]
        for (i in ticTacToe.indices) {
            answer = answer && (ticTacToe[i][ticTacToe.size - 1 - i] == result)
        }

        return if (answer)
            result
        else
            null
    }

    // 모든 배열이 체크가 되었는지
    private val isFull: Boolean
        get() = ticTacToe.all { arrayOx -> arrayOx.all { it != null } }

    // isFull && 게임 승부가 나지 않은 상태
    private val isDraw: Boolean
        get() = (getWinner() == null) && isFull

    // 체크 순서
    private var isX = OX.X

    // 새로운 배열 생성
    private fun newTicTacToe(): TicTacToeType = Array(3) { Array(3) { null } }

    override fun toString(): String {
        return ticTacToe.map {
            it.joinToString()
        }.joinToString("\n")
    }
}