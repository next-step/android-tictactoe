package com.example.domain

class Board(blocks: List<Block>) {
    private val blocks = blocks.toMutableList()

    val state: BoardState
        get() = BoardState(blocks.toList())

    init {
        require(blocks.size == BOARD_SIZE) {
            "${BOARD_SIZE}개의 블록이 필요합니다."
        }
    }

    fun assignBlock(turn: Turn, blockIndex: Int): BoardState {
        val nowBlock = blocks[blockIndex]
        require(nowBlock is EmptyBlock) {
            "이미 놓여진 블록입니다."
        }
        val assignedBlock = nowBlock.assign(turn.whoseTurn())
        blocks[blockIndex] = assignedBlock
        return BoardState(blocks)
    }

    fun isEmpty(): Boolean {
        return blocks.all { it is EmptyBlock }
    }

    fun isFull(): Boolean {
        return blocks.all { it is AssignedBlock }
    }

    fun hasOneLine(turn: Turn): Boolean {

        return when (turn.whoseTurn()) {
            Player.X -> winningIndexs.any { indexs ->
                indexs.map { blocks[it] }.all { it is XBlock }
            }
            Player.O -> winningIndexs.any { indexs ->
                indexs.map { blocks[it] }.all { it is OBlock }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (blocks != other.blocks) return false

        return true
    }

    override fun hashCode(): Int {
        return blocks.hashCode()
    }

    companion object {
        const val BOARD_SIZE = 9
        fun createEmptyBoard(): Board {
            val blocks = List(BOARD_SIZE) { EmptyBlock() }
            return Board(blocks)
        }

        val winningIndexs: List<List<Int>> = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6),
        )
    }
}
