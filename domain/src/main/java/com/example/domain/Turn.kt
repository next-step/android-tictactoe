package com.example.domain

@JvmInline
value class Turn(private val value: Int = INIT_TURN) {
    init {
        require(value in INIT_TURN until MAX_TURN) {
            "턴은 0보다 작거나, 8보다 클 수 없습니다."
        }
    }

    internal fun next(): Turn = Turn(value + 1)

    internal fun whoseTurn(): Player = if (value % 2 == 0) Player.X else Player.O

    companion object {
        internal const val INIT_TURN = 0
        internal const val MAX_TURN = Board.BOARD_SIZE
    }
}
