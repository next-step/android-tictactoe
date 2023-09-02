package camp.nextstep.edu.tictactoe.domain

data class Board private constructor(
    private val board: Map<Position, Cell>
) {
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
