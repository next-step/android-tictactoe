package com.example.domain

data class BoardState(val blocks: List<Block> = List(Board.BOARD_SIZE) { EmptyBlock() }) {
    init {
        require(blocks.size == Board.BOARD_SIZE) {
            "${Board.BOARD_SIZE}개의 블록이 필요합니다."
        }
    }
}
