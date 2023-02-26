package com.example.domain

import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

fun createWinnningBlocks(indexes: List<Int>, turn: Turn): Board {
    val blocks = mutableListOf<Block>()
    for (i in 0..8) {
        if (indexes.contains(i)) {
            val assignedBlock = if (turn.whoseTurn() == Player.O) OBlock else XBlock
            blocks.add(assignedBlock)
        } else {
            blocks.add(EmptyBlock())
        }
    }
    return Board(blocks)
}

@RunWith(Enclosed::class)
class BoardTest {

    class NonParameterizedTest {
        @Test
        fun `3x3의 빈 보드를 생성할 수 있다`() {
            // when
            val board = Board.createEmptyBoard()

            // then
            assertTrue(board.isEmpty())
            assertEquals(9, board.blocks.size)
        }

        @Test
        fun `길이가 9인 배열을 주입해서 생성할 수 있다`() {
            // given
            val blocks = listOf(
                XBlock,
                OBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
            )

            // when
            val board = Board(blocks)

            // then
            assertFalse(board.isEmpty())
            assertEquals(9, board.blocks.size)
        }

        @Test
        fun `길이가 9가 아닌 배열을 주입하면 예외를 던진다`() {
            // given
            val blocks = listOf(
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
            )

            // when
            val exception = assertThrows(IllegalArgumentException::class.java) {
                Board(blocks)
            }

            // then
            assertEquals("9개의 블록이 필요합니다.", exception.message)
        }


        @Test
        fun `하나라도 할당하면 isEmpty는 false를 반환한다`() {
            // given
            val board = Board.createEmptyBoard()
            assertTrue(board.isEmpty())

            // when
            board.assignBlock(Turn(0), 0)

            // then
            assertFalse(board.isEmpty())
        }

        @Test
        fun `Empty Block이 없으면 isDraw는 true를 반환한다`() {
            // given
            val board = Board.createEmptyBoard()
            assertFalse(board.isFull())

            // when
            board.assignBlock(Turn(0), 0)
            board.assignBlock(Turn(1), 1)
            board.assignBlock(Turn(2), 2)
            board.assignBlock(Turn(3), 3)
            board.assignBlock(Turn(4), 4)
            board.assignBlock(Turn(5), 5)
            board.assignBlock(Turn(6), 6)
            board.assignBlock(Turn(7), 7)
            board.assignBlock(Turn(8), 8)

            // then
            assertTrue(board.isFull())
        }
    }

    @RunWith(value = Parameterized::class)
    class WinningTest(private val board: Board, private val turn: Int) {
        @Test
        fun `승리 조건 테스트`() {
            // when
            val isWinning = board.hasOneLine(Turn(turn))

            // then
            assertTrue(isWinning)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}위치가 채워지면 {1}턴 플레이어의 승리다")
            fun winner(): Collection<Array<Any>> {
                return buildList {
                    addAll(Board.winningIndexs.map { indexes ->
                        arrayOf(createWinnningBlocks(indexes, Turn(0)), 0)
                    }.toList())
                    addAll(Board.winningIndexs.map { indexes ->
                        arrayOf(createWinnningBlocks(indexes, Turn(1)), 1)
                    }.toList())
                }
            }
        }
    }
}
