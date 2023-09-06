package com.example.tictectoe_domain



sealed class Cell(open val position: Position) {
    data class NONE(override val position: Position) : Cell(position)
    data class PLAYER1(override val position: Position) : Cell(position)
    data class PLAYER2(override val position: Position) : Cell(position)
}
