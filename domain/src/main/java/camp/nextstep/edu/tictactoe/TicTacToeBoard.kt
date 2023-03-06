package camp.nextstep.edu.tictactoe


class TicTacToeBoard {
    private var board: Array<Array<OX?>> = Array(3) { Array(3) { null } }

    private var boardEmptyCellCount = 9

    internal fun reset() {
        boardEmptyCellCount = 9
        board = newBoard()
    }

    internal fun getTicTacToeCell(position: Position): OX? {
        return board[position.row][position.column]
    }

    internal fun put(position: Position, ox: OX) {
        board[position.row][position.column] = ox
        boardEmptyCellCount--
    }

    fun getAllCell(): List<List<OX?>> {
        val allCellList: MutableList<MutableList<OX?>> = mutableListOf()
        board.forEach {
            val rowList = mutableListOf<OX?>()
            rowList.addAll(it)
            allCellList.add(rowList)
        }

        return allCellList
    }

    internal fun isExistedPosition(position: Position): Boolean {
        return position.row < 3 && position.column < 3 && position.row >= 0 && position.column >= 0
    }

    internal fun isRowWin(): OX? {
        for (i in board.indices) {
            var answer = true
            val result = board[0][i] ?: continue
            for (j in board.indices) {
                answer = answer && board[j][i] == result
            }

            if (answer)
                return result
        }

        return null
    }

    internal fun isColumnWin(): OX? {
        for (i in board.indices) {
            if (board[i].all { it == OX.X }) {
                return OX.X
            }

            if (board[i].all { it == OX.O }) {
                return OX.O
            }
        }

        return null
    }

    internal fun isDiagonalWin(): OX? {
        var answer = true
        val result = board[0][0]
        for (i in board.indices) {
            answer = answer && (board[i][i] == result)
        }

        return if (answer)
            result
        else
            null
    }

    internal fun isReverseDiagonalWin(): OX? {
        var answer = true
        val result = board[0][board.size - 1]
        for (i in board.indices) {
            answer = answer && (board[i][board.size - 1 - i] == result)
        }

        return if (answer)
            result
        else
            null
    }

    internal fun isEmptyCell(position: Position): Boolean {
        return board[position.row][position.column] == null
    }

    internal fun isFullBoard(): Boolean {
        return boardEmptyCellCount == 0
    }

    internal fun getRandomEmptyCell(isTest: Boolean = false): Position {

        val emptyCellList = mutableListOf<Position>()
        board.forEachIndexed { row, oxen ->
            oxen.forEachIndexed { column, ox ->
                if (ox == null)
                    emptyCellList.add(Position(row, column))
            }
        }

        return if (isTest)
            emptyCellList.first()
        else
            emptyCellList.random()
    }

    private fun newBoard(): Array<Array<OX?>> = Array(3) { Array(3) { null } }

    override fun toString(): String = board.joinToString("\n") { it.joinToString() }
}