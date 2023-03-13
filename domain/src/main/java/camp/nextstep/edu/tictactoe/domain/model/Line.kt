package camp.nextstep.edu.tictactoe.domain.model

data class Line(
    private val cells: Set<Cell>,
) {
    init {
        require(cells.size == 3) { "cell size가 3이 되어야 합니다." }
    }

    fun isX(): Boolean {
        return cells.filterIsInstance<Cell.X>().size == 3
    }

    fun isO(): Boolean {
        return cells.filterIsInstance<Cell.O>().size == 3
    }

    fun isDraw(): Boolean {
        return cells.filterIsInstance<Cell.Empty>().isEmpty()
                && cells.filterIsInstance<Cell.O>().size < 3
                && cells.filterIsInstance<Cell.X>().size < 3
    }

    fun findXWinningPosition(): Position? {
        if (cells.filterIsInstance<Cell.Empty>().size == 1
            && cells.filterIsInstance<Cell.X>().size == 2
        ) {
            return cells.find { it is Cell.Empty }?.position
        }
        return null
    }

    fun findOWinningPosition(): Position? {
        if (cells.filterIsInstance<Cell.Empty>().size == 1
            && cells.filterIsInstance<Cell.O>().size == 2
        ) {
            return cells.find { it is Cell.Empty }?.position
        }
        return null
    }

    companion object {
        fun of(cell1: Cell, cell2: Cell, cell3: Cell): Line {
            return Line(setOf(cell1, cell2, cell3))
        }
    }

}
