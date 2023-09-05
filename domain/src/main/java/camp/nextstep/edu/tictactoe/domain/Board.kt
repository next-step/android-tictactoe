package camp.nextstep.edu.tictactoe.domain

data class Board(
    private val board: Map<Position, Cell>
) {

    val boardSize
        get() = board.count { (_, cell) ->
            (cell as? Cell.Empty) == null
        }

    operator fun get(position: Position): Cell {
        return board[position] ?: throw IllegalArgumentException("해당 위치에는 아무것도 없습니다.")
    }

    fun set(position: Position, cell: Cell): Board {
        return if (board[position] is Cell.Empty) {
            this.copy(board = board + mapOf(position to cell))
        } else {
            throw IllegalArgumentException("이미 마크된 곳입니다.")
        }
    }

    fun filter(predicate: (Map.Entry<Position, Cell>) -> Boolean): Map<Position, Cell> {
        return board.filter(predicate)
    }

    fun getEmptyPositions(): List<Position> {
        return board.filter { (_, cell) -> cell is Cell.Empty }.keys.toList()
    }

    fun isFull(): Boolean {
        return board.all { (_, cell) -> cell !is Cell.Empty }
    }

    companion object {
        val EMPTY = Board(
            setOf(
                Cell.Empty(Position.TOP_LEFT),
                Cell.Empty(Position.TOP_CENTER),
                Cell.Empty(Position.TOP_RIGHT),
                Cell.Empty(Position.CENTER_LEFT),
                Cell.Empty(Position.CENTER_CENTER),
                Cell.Empty(Position.CENTER_RIGHT),
                Cell.Empty(Position.BOTTOM_LEFT),
                Cell.Empty(Position.BOTTOM_CENTER),
                Cell.Empty(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )
    }
}
