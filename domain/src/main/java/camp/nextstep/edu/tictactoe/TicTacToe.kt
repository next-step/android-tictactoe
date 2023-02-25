package camp.nextstep.edu.tictactoe


class TicTacToe {
    private var ticTacToe: Array<Array<OX?>> = Array(3) { Array(3) { null } }
    private var gameStatus = GameStatus(ticTacToe)


    fun put(x: Int, y: Int) {
        require(gameStatus.currentGameStatus == TicTacToeStatus.PLAYING) {
            "게임이 종료 되었습니다."
        }

        require(!isNotExistedPosition(x, y)) {
            "잘못된 위치입니다."
        }

        if (ticTacToe[x][y] == null) {
            ticTacToe[x][y] = gameStatus.gameTern
        }
    }

    fun getGameStatus() : TicTacToeStatus {
        return gameStatus.currentGameStatus
    }

    fun getTicTacToeCell(x: Int, y: Int): OX? {
        return ticTacToe[x][y]
    }

    fun getAllCell() = ticTacToe.copyOf()

    fun reset() {
        ticTacToe = newTicTacToe()
        gameStatus = GameStatus(ticTacToe)
    }

    private fun isNotExistedPosition(x: Int, y: Int): Boolean {
        return x >= ticTacToe.size || y >= ticTacToe[x].size || x < 0 || y < 0
    }

    // 새로운 배열 생성
    private fun newTicTacToe(): Array<Array<OX?>> = Array(3) { Array(3) { null } }

    override fun toString(): String = ticTacToe.joinToString("\n") { it.joinToString() }
}