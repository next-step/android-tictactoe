package com.nextstep.edu.tictactoe.domain.model

data class RandomBehavior(
    val behavior: Behavior,
    val point: Point
)

enum class Behavior {
    INTERRUPT, WIN, UNKNOWN
}

sealed class Lines {
    object leftDiagonal: Lines()
    object rightDiagonal: Lines()
    class row(val rowIndex: Int): Lines()
    class column(val columnIndex: Int): Lines()
}