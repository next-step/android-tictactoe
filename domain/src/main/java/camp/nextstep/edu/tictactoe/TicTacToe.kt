package camp.nextstep.edu.tictactoe


class TicTacToe {
    private var ticTacToe: Array<Array<OX?>> = Array(3) { Array(3) { null } }

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