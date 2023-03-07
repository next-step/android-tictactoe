package com.example.domain

internal interface AssignAlgorithm {
    fun calculateNextDoing(boardState: BoardState): Int
}
