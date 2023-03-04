package com.example.domain

import org.junit.Test
import org.junit.Assert.*


class GameTest {
    @Test
    fun `기본생성자는 0턴 ongoing emptyBlock RandomMode 로 생성된다`() {
        // when
        val game = Game()

        // then
        assertEquals(game.state.turn, Turn(0))
        assertEquals(game.state.status, GameStatus.ONGOING)
        assertTrue(game.gameMode is RandomMode)
        assertEquals(game.state.board.blocks, List(9) { EmptyBlock() })
    }

    @Test
    fun `assignBlock 이후 게임이 끝나지 않으면 Turn이 1 증가한다`() {
        // given
        val game = Game(gameMode = TwoPlayerMode)
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
            ), gameMode = TwoPlayerMode
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
            ), gameMode = TwoPlayerMode
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
            ), gameMode = TwoPlayerMode
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
            ), gameMode = TwoPlayerMode
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
                ),
            ), gameMode = TwoPlayerMode
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
        assertTrue(game.gameMode is RandomMode)
        assertEquals(game, Game())
    }

    @Test
    fun `changeMode 를 통해 게임 모드를 변경할 수 있다`() {
        // given
        val game = Game()
        assertTrue(game.gameMode is RandomMode)

        // when
        game.changeMode(TwoPlayerMode)

        // then
        assertEquals(game.gameMode, TwoPlayerMode)
    }

    @Test
    fun `같은 모드로 변경을 시도하면 에러를 반환한다`() {
        // given
        val game = Game()
        assertTrue(game.gameMode is RandomMode)

        // when
        val exception = assertThrows(IllegalArgumentException::class.java) {
            game.changeMode(RandomMode())
        }

        // then
        assertEquals("같은 모드로 변경하실 수 없습니다.", exception.message)
    }

    @Test
    fun `랜덤모드에서 수를 두면 AI가 다음 수를 둔다`() {
        // given
        val game = Game(gameMode = RandomMode(algorithm = FirstEmptyBlockStrategy()))
        assertTrue(game.gameMode is RandomMode)

        // when
        game.assignBlock(0)

        // then
        assertEquals(
            game.state.board.blocks,
            listOf(
                XBlock,
                OBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )
    }
}
