package camp.nextstep.edu.tictactoe

class TicTacToe(
    private var ticTacToe: Array<Array<OX?>> = Array(3) { Array(3) { null } },
) {

    private var isX = OX.X

    var isDraw: Boolean = false
        private set
        get() = getWin() == null &&
            ticTacToe.all { arrayOx -> arrayOx.all { it != null } }

    fun put(x: Int, y: Int) {
        if (ticTacToe[x][y] != null) return

        ticTacToe[x][y] = isX
        isX = isX.change()
    }

    fun getTicTacToeCell(x: Int, y: Int): OX? {
        return ticTacToe[x][y]
    }

    fun getAllCell() = ticTacToe.copyOf()

    fun reset() {
        ticTacToe = Array(3) { Array(3) { null } }
    }

    fun getWin(): OX? {
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


//        ticTacToe[0].all { it == OX.X }
//        ticTacToe[1].all { it == OX.X }
//        ticTacToe[2].all { it == OX.X }
//
//        ticTacToe[0][1] == OX.X &&
//            ticTacToe[1][1] == OX.X &&
//            ticTacToe[2][1] == OX.X -> OX.X
//
//        ticTacToe[0][0] == OX.X &&
//            ticTacToe[1][1] == OX.X &&
//            ticTacToe[2][2] == OX.X -> OX.X
//
//        ticTacToe[0][2] == OX.X &&
//            ticTacToe[1][1] == OX.X &&
//            ticTacToe[2][0] == OX.X -> OX.X
//
//
//        ticTacToe[0].all { it == OX.O }
//        ticTacToe[1].all { it == OX.O }
//        ticTacToe[2].all { it == OX.O }
//
//        (ticTacToe[0][0] == OX.O &&
//            ticTacToe[1][0] == OX.O &&
//            ticTacToe[2][0] == OX.O) -> OX.O
//
//        (ticTacToe[0][2] == OX.O &&
//            ticTacToe[1][2] == OX.O &&
//            ticTacToe[2][2] == OX.O) -> OX.O
//
//        (ticTacToe[0][0] == OX.O &&
//            ticTacToe[1][1] == OX.O &&
//            ticTacToe[2][2] == OX.O) -> OX.O
//
//        (ticTacToe[0][2] == OX.O &&
//            ticTacToe[1][1] == OX.O &&
//            ticTacToe[2][0] == OX.O) -> OX.O
//
//        else -> null
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


    override fun toString(): String {
        return ticTacToe.map {
            it.joinToString()
        }.joinToString("\n")
    }
}