package camp.nextstep.edu.tictactoe.domain.model

sealed class Cell(open val position: Position) {
    data class Empty(override val position: Position) : Cell(position)
    data class O(override var position: Position) : Cell(position)
    data class X(override val position: Position) : Cell(position)
}