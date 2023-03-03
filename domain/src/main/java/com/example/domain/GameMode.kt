package com.example.domain

sealed class GameMode

object TwoPlayerMode : GameMode()

class RandomMode(private val algorithm: AssignAlgorithm = RandomBlockStrategy()) : GameMode() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun calculateNextDoing(boardState: BoardState): Int {
        return algorithm.calculateNextDoing(boardState)
    }
}
