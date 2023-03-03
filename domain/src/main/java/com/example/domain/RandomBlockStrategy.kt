package com.example.domain

class RandomBlockStrategy : AssignAlgorithm {
    override fun calculateNextDoing(boardState: BoardState): Int {
        val emptyBlockIndexList: List<Int> = boardState.blocks.mapIndexed { index, block ->
            if (block is EmptyBlock) {
                index
            } else {
                null
            }
        }.filterNotNull()

        return emptyBlockIndexList.random()
    }
}
