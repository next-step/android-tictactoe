package com.example.domain

internal class DrawBlockStrategy : AssignAlgorithm {
    override fun calculateNextDoing(boardState: BoardState): Int {
        // 중앙이 비어있으면 중앙을 먹는다.
        if (isCenterEmpty(boardState)) {
            return CENTER
        }
        // 내가 이기는 수가 있으면 이기는 수를 둔다.
        val computerWinDoing = hasWinsDoingOrNull(boardState, OBlock)
        if (computerWinDoing != null) return computerWinDoing

        // 상대가 이기는 수가 있으면 막는다.
        val playerWinDoing = hasWinsDoingOrNull(boardState, XBlock)
        if (playerWinDoing != null) return playerWinDoing

        // 코너에 둔다.
        val corner = getFirstEmptyCorner(boardState)
        if (corner != null) return corner

        // 남는 곳에 둔다
        return boardState.blocks.indexOfFirst { it is EmptyBlock }
    }

    private fun isCenterEmpty(boardState: BoardState): Boolean {
        return boardState.blocks[CENTER] is EmptyBlock
    }

    private fun hasWinsDoingOrNull(boardState: BoardState, whose: Block): Int? {
        return Board.winningIndexs.find {
            it.filter { index -> boardState.blocks[index] is EmptyBlock }.size == 1
                    && it.filter { index -> boardState.blocks[index] == whose }.size == 2
        }?.filter { index -> boardState.blocks[index] is EmptyBlock }?.get(0)
    }

    private fun getFirstEmptyCorner(boardState: BoardState): Int? {
        return CORNER.find { boardState.blocks[it] is EmptyBlock }
    }

    companion object {
        private const val CENTER = 4
        private val CORNER = listOf(0, 2, 6, 8)
    }
}
