package com.nextstep.edu.tictactoe.domain.model

data class RandomBehavior(
    val behavior: Behavior,
    val point: Point
)

enum class Behavior {
    INTERRUPT, WIN, UNKNOWN
}