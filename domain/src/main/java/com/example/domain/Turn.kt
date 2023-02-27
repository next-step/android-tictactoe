package com.example.domain

@JvmInline
value class Turn(private val value: Int) {
    init {
        require(value in 0..8) {
            "턴은 0보다 작거나, 8보다 클 수 없습니다."
        }
    }

    fun next(): Turn = Turn(value + 1)

    fun whoseTurn(): Player = if (value % 2 == 0) Player.X else Player.O
}
