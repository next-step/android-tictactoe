package com.example.domain

internal class DrawBlockStrategy : AssignAlgorithm {
    override fun calculateNextDoing(boardState: BoardState): Int {
        // 중앙이 비어있으면 중앙을 먹는다.
        if (isCenterEmpty(boardState)) {
            return CENTER
        }
        // 내가 이기는 수가 있으면 이기는 수를 둔다.
        val computerWinDoing = findWin(boardState, OBlock)
        if (computerWinDoing != null) return computerWinDoing

        // 상대가 이기는 수가 있으면 막는다.
        val playerWinDoing = findWin(boardState, XBlock)
        if (playerWinDoing != null) return playerWinDoing

        // 상대가 포크를 만들 수 있으면 막는다.
        val playerFork = findPlayerFork(boardState)
        if (playerFork != null) return playerFork

        // 상대가 가진 코너가 1개일 경우 반대편 에 둔다
        val oppositeCorner = findOppositeCorner(boardState)
        if (oppositeCorner != null) return oppositeCorner

        // 코너에 둔다.
        val corner = findCorner(boardState)
        if (corner != null) return corner

        // 남는 곳에 둔다
        return boardState.blocks.indexOfFirst { it is EmptyBlock }
    }

    private fun isCenterEmpty(boardState: BoardState): Boolean {
        return boardState.blocks[CENTER] is EmptyBlock
    }

    private fun findWin(boardState: BoardState, whose: Block): Int? {
        return Board.winningIndexs.find {
            it.filter { index -> boardState.blocks[index] is EmptyBlock }.size == 1
                    && it.filter { index -> boardState.blocks[index] == whose }.size == 2
        }?.filter { index -> boardState.blocks[index] is EmptyBlock }?.get(0)
    }

    private fun findPlayerFork(boardState: BoardState): Int? {
        if (CORNER.count { boardState.blocks[it] is EmptyBlock } != 2 || CORNER.count { boardState.blocks[it] is XBlock } != 2) return null
        return when (CORNER.filter { boardState.blocks[it] is XBlock }) {
            listOf(0, 8) -> findSide(boardState)
            listOf(2, 6) -> findSide(boardState)
            else -> null
        }
    }

    private fun findSide(boardState: BoardState): Int? {
        return listOf(1, 3, 5, 7).find { boardState.blocks[it] is EmptyBlock }
    }

    private fun findOppositeCorner(boardState: BoardState): Int? {
        if (CORNER.filter { boardState.blocks[it] is EmptyBlock }.size != 3 || CORNER.count { boardState.blocks[it] is XBlock } != 1) return null
        return when (CORNER.find { boardState.blocks[it] is XBlock }) {
            0 -> 8
            2 -> 6
            6 -> 2
            8 -> 0
            else -> null
        }
    }

    private fun findCorner(boardState: BoardState): Int? {
        return CORNER.find { boardState.blocks[it] is EmptyBlock }
    }

    companion object {
        private const val CENTER = 4
        private val CORNER = listOf(0, 2, 6, 8)
    }
}
