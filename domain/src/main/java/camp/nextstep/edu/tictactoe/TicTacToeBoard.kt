package camp.nextstep.edu.tictactoe

import camp.nextstep.edu.tictactoe.GameMode.*


class TicTacToeBoard {
    private var ticTacToe: Array<Array<OX?>> = Array(3) { Array(3) { null } }
    private var gameStatus = GameStatus(this)
    var gameMode = TWO_PLAYERS


    fun put(x: Int, y: Int) {
        if (gameStatus.currentGameStatus != TicTacToeStatus.PLAYING) return
        require(!isNotExistedPosition(x, y)) {
            "잘못된 위치입니다."
        }

        if (ticTacToe[x][y] != null) return
        when (gameMode) {
            TWO_PLAYERS -> twoPlayerPut(x, y)
            RANDOM -> {
                twoPlayerPut(x, y)
                randomInput(RandomInput.getRandomPositionList(this))
            }
            DRAW -> TODO()
        }
    }

    // 2인
    internal fun twoPlayerPut(x: Int, y: Int) {
        ticTacToe[x][y] = gameStatus.gameTern
    }

    // 랜덤
    internal fun randomInput(randomPositionList: List<Pair<Int, Int>>) {
        if (gameStatus.currentGameStatus != TicTacToeStatus.PLAYING) return

        val randomPosition = RandomInput.getRandomPosition(randomPositionList)
        twoPlayerPut(randomPosition.first, randomPosition.second)
    }

    fun getGameStatus(): TicTacToeStatus {
        return gameStatus.currentGameStatus
    }

    fun getTicTacToeCell(x: Int, y: Int): OX? {
        return ticTacToe[x][y]
    }

    fun getAllCell() = ticTacToe.copyOf()

    fun reset() {
        ticTacToe = newTicTacToe()
        gameStatus = GameStatus(this)
    }

    private fun isNotExistedPosition(x: Int, y: Int): Boolean {
        return x >= ticTacToe.size || y >= ticTacToe[x].size || x < 0 || y < 0
    }

    // 새로운 배열 생성
    private fun newTicTacToe(): Array<Array<OX?>> = Array(3) { Array(3) { null } }

    override fun toString(): String = ticTacToe.joinToString("\n") { it.joinToString() }

    internal fun isColumn(): OX? {
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

    internal fun isRow(): OX? {
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

    internal fun isDiagonal(): OX? {
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

    internal fun isReverseDiagonal(): OX? {
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
}