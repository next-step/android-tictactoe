package camp.nextstep.edu.tictactoe

class Winning {
    fun getWinner(ticTacToe: Array<Array<OX?>>): OX? {
        val isRowWin = isRow(ticTacToe)
        val isColumnWin = isColumn(ticTacToe)
        val isDiagonal = isDiagonal(ticTacToe)
        val isReverseDiagonal = isReverseDiagonal(ticTacToe)

        return when {
            isRowWin != null -> isRowWin
            isColumnWin != null -> isColumnWin
            isDiagonal != null -> isDiagonal
            isReverseDiagonal != null -> isReverseDiagonal
            else -> null
        }
    }

    private fun isColumn(ticTacToe: Array<Array<OX?>>): OX? {
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

    private fun isRow(ticTacToe: Array<Array<OX?>>): OX? {
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

    private fun isDiagonal(ticTacToe: Array<Array<OX?>>): OX? {
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

    private fun isReverseDiagonal(ticTacToe: Array<Array<OX?>>): OX? {
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