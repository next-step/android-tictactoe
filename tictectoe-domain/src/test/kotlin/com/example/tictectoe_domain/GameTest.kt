package com.example.tictectoe_domain

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GameTest {

    private lateinit var game: Game
    private lateinit var board: TictectoeBoard
    private lateinit var rule: TictectoeRule

    @Before
    fun init() {
        board = TictectoeBoard()
        rule = TictectoeRule()
        game = Game(board, rule)
    }

    @Test
    fun `첫 순서에 보드를 선택하면 해당 위치에 Player1이 차지한다`() {
        // given :


        // when : 보드의 한 위치를 선택한다.
        game.selectBoard(1)

        // then : 보드의 해당 부분이 player1로 바뀝니다.
        assertThat(board.getBoard()[1]).isEqualTo(Player.PLAYER1)
    }

    @Test
    fun `두번째 순서에 보드를 선택하면 해당 위치에 Player2이 차지한다`() {
        // given :


        // when : 보드에서 각각 다른 위치를 선택한다.
        game.selectBoard(1)
        game.selectBoard(2)

        // then : 보드의 해당 부분이 player2로 바뀝니다.
        assertThat(board.getBoard()[2]).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `선택됐던 보드를 선택하면 변화가 없다`() {
        // given :


        // when : 보드의 왼쪽 위에를 선택한다.
        game.selectBoard(1)
        if(game.canSelect(1)) {
            game.selectBoard(1)
        }


        // then : 보드의 해당 부분이 player1이 그대로 입니다.
        assertThat(board.getBoard()[1]).isEqualTo(Player.PLAYER1)
    }
}
