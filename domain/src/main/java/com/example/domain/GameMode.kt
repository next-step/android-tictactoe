package com.example.domain

sealed class GameMode

object TwoPlayerMode : GameMode()

class RandomMode : GameMode() {

    private var algorithm: AssignAlgorithm

    init {
        algorithm = RandomBlockStrategy()
    }

    internal fun changeAlgorithm(assignAlgorithm: AssignAlgorithm) {
        algorithm = assignAlgorithm
    }

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

class DrawMode : GameMode() {

    private val algorithm: AssignAlgorithm = DrawBlockStrategy()
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
