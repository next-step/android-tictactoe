package com.example.domain

class FirstEmptyBlockStrategy : AssignAlgorithm {
    override fun calculateNextDoing(boardState: BoardState): Int {
        return boardState.blocks.indexOfFirst { it is EmptyBlock }
    }
}
