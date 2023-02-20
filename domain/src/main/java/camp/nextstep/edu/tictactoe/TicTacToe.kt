package camp.nextstep.edu.tictactoe

class TicTacToe {
    private var ticTacToe: Array<Array<OX?>> = Array(3) { Array(3) { null } }
    private var isX = OX.X

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
}