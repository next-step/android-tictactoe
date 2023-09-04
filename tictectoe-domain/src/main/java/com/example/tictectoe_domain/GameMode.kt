package com.example.tictectoe_domain

import kotlin.random.Random

enum class GameMode : SelectBoard {
    TWO_PLAYER {
        override fun selectBoard(board: TictectoeBoard, position: Int, playerTurn: PlayerTurn) {
            board.selectBoard(position, playerTurn)
        }
    },
    RANDOM {
        override fun selectBoard(board: TictectoeBoard, position: Int, playerTurn: PlayerTurn) {
            board.selectBoard(position, playerTurn)

            if(board.tictectoeBoard.count { it == Cell.NONE } == 1) {
                return
            }

            // 빈칸을 찾는다.
            val playerNoneIndexList = mutableListOf<Int>()

            // 빈칸 리스트에 추가
            for ((index, player) in board.tictectoeBoard.withIndex()) {
                if (player == Cell.NONE) {
                    playerNoneIndexList.add(index)
                }
            }
            // 빈칸 중 0은 제외 한다.
            playerNoneIndexList.removeAt(0)

            // 랜덤으로 선택
            val selectIndex = Random(System.nanoTime()).nextInt(playerNoneIndexList.size)

            board.selectBoard(playerNoneIndexList[selectIndex], PlayerTurn.TURN_PLAYER2)
        }
    },
    INTERMEDIATE_LEVEL {
        override fun selectBoard(board: TictectoeBoard, position: Int, playerTurn: PlayerTurn) {
            board.selectBoard(position, playerTurn)
        }
    };

}

interface SelectBoard {
    fun selectBoard(board: TictectoeBoard, position: Int, playerTurn: PlayerTurn)
}
