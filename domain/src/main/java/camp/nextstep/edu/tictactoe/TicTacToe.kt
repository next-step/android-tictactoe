package camp.nextstep.edu.tictactoe

typealias TicTacToeType = Array<Array<OX?>>

class TicTacToe(
    private var ticTacToe: TicTacToeType = Array(3) { Array(3) { null } },
) {

    var currentGameStatus: TicTacToeStatus = TicTacToeStatus.PLAYING
        private set

    init {
        currentGameStatus = getGameStatus()
    }

    fun put(x: Int, y: Int) {
        require(currentGameStatus == TicTacToeStatus.PLAYING) {
            "게임이 종료 되었습니다."
        }

        require(!isNotExistedPosition(x, y)) {
            "잘못된 위치입니다."
        }

        if (ticTacToe[x][y] == null) {
            putCount++
            ticTacToe[x][y] = isX
            isX = isX.change()
            currentGameStatus = getGameStatus()
        }
    }

    private fun getGameStatus(): TicTacToeStatus {
        val winner = getWinner()
        return when {
            isDraw(winner == null) -> TicTacToeStatus.DRAW
            winner == OX.X -> TicTacToeStatus.X_WIN
            winner == OX.O -> TicTacToeStatus.O_WIN
            else -> TicTacToeStatus.PLAYING
        }
    }

    fun getTicTacToeCell(x: Int, y: Int): OX? {
        return ticTacToe[x][y]
    }

    fun getAllCell() = ticTacToe.copyOf()

    fun reset() {
        putCount = 0
        ticTacToe = newTicTacToe()
        isX = OX.X
        currentGameStatus = getGameStatus()
    }

    private fun isNotExistedPosition(x: Int, y: Int): Boolean {
        return x >= ticTacToe.size || y >= ticTacToe[x].size || x < 0 || y < 0
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
            val result = ticTacToe[0][i] ?: continue
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
    private var putCount = 0

    // 배열이 가득 차거나 게임 승부가 나지 않은 상태
    private fun isDraw(isNotExistedWinner: Boolean): Boolean = isNotExistedWinner && (putCount == 9)

    // 체크 순서
    private var isX = OX.X

    // 새로운 배열 생성
    private fun newTicTacToe(): Array<Array<OX?>> = Array(3) { Array(3) { null } }

    override fun toString(): String = ticTacToe.joinToString("\n") { it.joinToString() }
}