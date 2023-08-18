package com.nextstep.edu.tictactoe.domain

class DefaultTictactoe(
    val strategy: TictactocStrategy
) {
    fun reset() = strategy.resetMap()

    fun changeTurn() = strategy.changeTurn()

    fun getCurrentTurn() = strategy.getCurrentTurn()

    companion object {
        const val MAP_SIZE = 3
    }
}
