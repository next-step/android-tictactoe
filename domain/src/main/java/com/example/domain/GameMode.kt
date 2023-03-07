package com.example.domain

internal sealed class GameMode

internal object TwoPlayerMode : GameMode()

internal class RandomMode(private val algorithm: AssignAlgorithm = RandomBlockStrategy()) :
    GameMode() {
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

internal class DrawMode(private val algorithm: AssignAlgorithm = DrawBlockStrategy()) : GameMode() {
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
