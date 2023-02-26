package com.example.domain

class Board(blocks: List<Block>) {
    private val _blocks = blocks.toMutableList()
    val blocks: List<Block> = _blocks.toList()

    init {
        require(blocks.size == 9) {
            "9개의 블록이 필요합니다."
        }
    }

    fun assignBlock(turn: Turn, blockIndex: Int): Board {
        val nowBlock = _blocks[blockIndex]
        require(nowBlock is EmptyBlock) {
            "이미 놓여진 블록입니다."
        }
        val assignedBlock = nowBlock.assign(turn.whoseTurn())
        _blocks[blockIndex] = assignedBlock
        return Board(_blocks)
    }

    fun isEmpty(): Boolean {
        return _blocks.all { it is EmptyBlock }
    }

    fun isDraw(): Boolean {
        return _blocks.all { it is AssignedBlock }
    }

    fun isWinning(turn: Turn): Boolean {

        return when (turn.whoseTurn()) {
            Player.X -> winningIndexs.any { indexs ->
                indexs.map { _blocks[it] }.all { it is XBlock }
            }
            Player.O -> winningIndexs.any { indexs ->
                indexs.map { _blocks[it] }.all { it is OBlock }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (_blocks != other._blocks) return false

        return true
    }

    override fun hashCode(): Int {
        return _blocks.hashCode()
    }

    override fun toString(): String {
        return "Board(blocks=$blocks)"
    }

    companion object {
        fun createEmptyBoard(): Board {
            val blocks = List(9) { EmptyBlock() }
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
