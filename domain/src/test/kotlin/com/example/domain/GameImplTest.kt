package com.example.domain

import org.junit.Test
import org.junit.Assert.*


class GameImplTest {
    @Test
    fun `기본생성자는 0턴 ongoing emptyBlock RandomMode 로 생성된다`() {
        // when
        val gameImpl: GameImpl = GameImpl.createEmptyGame()

        // then
        assertEquals(gameImpl.turn, Turn(0))
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)
        assertTrue(gameImpl.gameMode is DrawMode)
        assertEquals(gameImpl.state.board.blocks, List(9) { EmptyBlock() })
    }

    @Test
    fun `assignBlock 이후 게임이 끝나지 않으면 Turn이 1 증가한다`() {
        // given
        val gameImpl = GameImpl.createEmptyGame()
        gameImpl.changeMode(SelectMode.TwoPlayer)
        assertEquals(gameImpl.turn, Turn(0))

        // when
        gameImpl.assignBlock(0)

        // then
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)
        assertEquals(gameImpl.turn, Turn(1))
    }

    @Test
    fun `Draw가되면 Turn은 오르지않고 status가 DRAW로 변경된다`() {
        // given
        val gameImpl = GameImpl(
            Turn(8), BoardState(
                listOf(
                    XBlock, OBlock, XBlock, XBlock, OBlock, OBlock, OBlock, XBlock, EmptyBlock()
                )
            ), TwoPlayerMode
        )
        assertEquals(gameImpl.turn, Turn(8))

        // when
        gameImpl.assignBlock(8)

        // then
        assertEquals(gameImpl.state.status, GameStatus.DRAW)
        assertEquals(gameImpl.turn, Turn(8))
    }

    @Test
    fun `마지막 턴에 승리하는 경우 DRAW가 아니라 승리가 반환된다`() {
        // given
        val gameImpl = GameImpl(
            Turn(8), BoardState(
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
                ),
            ),
            TwoPlayerMode
        )
        assertEquals(gameImpl.turn, Turn(8))
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)

        // when
        gameImpl.assignBlock(6)

        // then
        assertEquals(gameImpl.state.status, GameStatus.X_WON)
        assertEquals(gameImpl.turn, Turn(8))
    }

    @Test
    fun `게임이 종료되었을 때 assign하면 Error를 던진다`() {
        // given
        val gameImpl = GameImpl(
            Turn(8), BoardState(
                listOf(
                    XBlock, OBlock, XBlock, XBlock, OBlock, OBlock, XBlock, OBlock, EmptyBlock()
                ),
            ),
            TwoPlayerMode
        )
        assertEquals(gameImpl.state.status, GameStatus.X_WON)

        // when
        val exception = assertThrows(IllegalArgumentException::class.java) {
            gameImpl.assignBlock(8)
        }
        // then
        assertEquals("게임이 종료되었습니다.", exception.message)
    }

    @Test
    fun `X가 승리하면 X_WON을 반환한다`() {
        // given
        val gameImpl = GameImpl(
            turn = Turn(6), board = BoardState(
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
                ),
            ),
            TwoPlayerMode
        )
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)

        // when
        gameImpl.assignBlock(6)

        // then
        assertEquals(gameImpl.state.status, GameStatus.X_WON)
    }

    @Test
    fun `O가 승리하면 O_WON을 반환한다`() {
        // given
        val gameImpl = GameImpl(
            turn = Turn(5), board = BoardState(
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
            ),
            TwoPlayerMode
        )
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)

        // when
        gameImpl.assignBlock(8)

        // then
        assertEquals(gameImpl.state.status, GameStatus.O_WON)
    }

    @Test
    fun `reset 하면 기본 기본 생성자로 생성한 Game과 같다 `() {
        // given
        val gameImpl = GameImpl.createEmptyGame()
        gameImpl.assignBlock(0)

        // when
        gameImpl.reset()

        // then
        assertEquals(gameImpl.turn, Turn(0))
        assertEquals(gameImpl.state.status, GameStatus.ONGOING)
        assertEquals(gameImpl.state.board.blocks, List(9) { EmptyBlock() })
        assertTrue(gameImpl.gameMode is DrawMode)
        assertEquals(gameImpl, GameImpl.createEmptyGame())
    }

    @Test
    fun `changeMode 를 통해 게임 모드를 변경할 수 있다`() {
        // given
        val gameImpl = GameImpl.createEmptyGame()
        assertTrue(gameImpl.gameMode is DrawMode)

        // when
        gameImpl.changeMode(SelectMode.TwoPlayer)

        // then
        assertEquals(gameImpl.gameMode, TwoPlayerMode)
    }

    @Test
    fun `같은 모드로 변경을 시도하면 에러를 반환한다`() {
        // given
        val gameImpl = GameImpl.createEmptyGame()
        assertTrue(gameImpl.gameMode is DrawMode)

        // when
        val exception = assertThrows(IllegalArgumentException::class.java) {
            gameImpl.changeMode(SelectMode.Draw)
        }

        // then
        assertEquals("같은 모드로 변경하실 수 없습니다.", exception.message)
    }

    @Test
    fun `랜덤모드에서 수를 두면 AI가 다음 수를 둔다`() {
        // given
        val gameImpl = GameImpl.createEmptyGame()
        gameImpl.changeRandomMode(FirstEmptyBlockStrategy())
        assertTrue(gameImpl.gameMode is RandomMode)

        // when
        gameImpl.assignBlock(0)

        // then
        assertEquals(
            gameImpl.state.board.blocks, listOf(
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
