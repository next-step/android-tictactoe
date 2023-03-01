package com.example.domain

import org.junit.Test
import org.junit.Assert.*


class GameTest {
    @Test
    fun `기본생성자는 0턴 ongoing emptyBlock으로 생성된다`() {
        // when
        val game = Game()

        // then
        assertEquals(game.state.turn, Turn(0))
        assertEquals(game.state.status, GameStatus.ONGOING)
        assertEquals(game.state.board.blocks, List(9) { EmptyBlock() })
    }

    @Test
    fun `assignBlock 이후 게임이 끝나지 않으면 Turn이 1 증가한다`() {
        // given
        val game = Game()
        assertEquals(game.state.turn, Turn(0))

        // when
        game.assignBlock(0)

        // then
        assertEquals(game.state.status, GameStatus.ONGOING)
        assertEquals(game.state.turn, Turn(1))
    }

    @Test
    fun `Draw가되면 Turn은 오르지않고 status가 DRAW로 변경된다`() {
        // given
        val game = Game(
            turn = 8, board = Board(
                listOf(
                    XBlock,
                    OBlock,
                    XBlock,
                    XBlock,
                    OBlock,
                    OBlock,
                    OBlock,
                    XBlock,
                    EmptyBlock()
                )
            )
        )
        assertEquals(game.state.turn, Turn(8))

        // when
        game.assignBlock(8)

        // then
        assertEquals(game.state.status, GameStatus.DRAW)
        assertEquals(game.state.turn, Turn(8))
    }

    @Test
    fun `마지막 턴에 승리하는 경우 DRAW가 아니라 승리가 반환된다`() {
        // given
        val game = Game(
            turn = 8, board = Board(
                listOf(
                    XBlock,
                    OBlock,
                    XBlock,
                    XBlock,
                    OBlock,
                    OBlock,
                    EmptyBlock(),
                    XBlock,
                    OBlock,
                )
            )
        )
        assertEquals(game.state.turn, Turn(8))
        assertEquals(game.state.status, GameStatus.ONGOING)

        // when
        game.assignBlock(6)

        // then
        assertEquals(game.state.status, GameStatus.X_WON)
        assertEquals(game.state.turn, Turn(8))
    }

    @Test
    fun `게임이 종료되었을 때 assign하면 Error를 던진다`() {
        // given
        val game = Game(
            turn = 8, board = Board(
                listOf(
                    XBlock,
                    OBlock,
                    XBlock,
                    XBlock,
                    OBlock,
                    OBlock,
                    XBlock,
                    OBlock,
                    EmptyBlock()
                )
            )
        )
        assertEquals(game.state.status, GameStatus.X_WON)

        // when
        val exception = assertThrows(IllegalArgumentException::class.java) {
            game.assignBlock(8)
        }
        // then
        assertEquals("게임이 종료되었습니다.", exception.message)
    }

    @Test
    fun `X가 승리하면 X_WON을 반환한다`() {
        // given
        val game = Game(
            turn = 6, board = Board(
                listOf(
                    XBlock,
                    OBlock,
                    XBlock,
                    XBlock,
                    OBlock,
                    OBlock,
                    EmptyBlock(),
                    EmptyBlock(),
                    EmptyBlock()
                )
            )
        )
        assertEquals(game.state.status, GameStatus.ONGOING)

        // when
        game.assignBlock(6)

        // then
        assertEquals(game.state.status, GameStatus.X_WON)
    }

    @Test
    fun `O가 승리하면 O_WON을 반환한다`() {
        // given
        val game = Game(
            turn = 5, board = Board(
                listOf(
                    XBlock,
                    XBlock,
                    OBlock,
                    XBlock,
                    EmptyBlock(),
                    OBlock,
                    EmptyBlock(),
                    EmptyBlock(),
                    EmptyBlock(),
                )
            )
        )
        assertEquals(game.state.status, GameStatus.ONGOING)

        // when
        game.assignBlock(8)

        // then
        assertEquals(game.state.status, GameStatus.O_WON)
    }

    @Test
    fun `reset 하면 기본 기본 생성자로 생성한 Game과 같다 `() {
        // given
        val game = Game()
        game.assignBlock(0)

        // when
        game.reset()

        // then
        assertEquals(game.state.turn, Turn(0))
        assertEquals(game.state.status, GameStatus.ONGOING)
        assertEquals(game.state.board.blocks, List(9) { EmptyBlock() })
        assertEquals(game, Game())
    }
}
