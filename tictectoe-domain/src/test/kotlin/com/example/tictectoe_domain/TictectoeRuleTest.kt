package com.example.tictectoe_domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class TictectoeRuleTest {

    private lateinit var gameRule: TictectoeRule

    @Before
    fun setTest() {
        gameRule = TictectoeRule()
    }

    @Test
    fun `상단 가로줄 모두 player1이 차지할 경우 승리한 자는 player1이다`() {
        // given : 상단 가로줄 모두 player1인 보드를 만든다.
        val board = Board.TEST_PLAYER1_WIN_BOARD

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.checkGameStatus(board)

        // then : 게임 상태는 GameStatus.PLAYER1_WIN을 반환한다.
        assertThat(actual).isEqualTo(GameStatus.PLAYER1_WIN)
    }

    @Test
    fun `모든 셀이 채워져도 승리하는 조건을 만족하지 않으면 무승부다`() {
        // given : 승리 조건을 만족하지 않는 보드를 만든다.
        val board = Board.TEST_DRAW_BOARD

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.checkGameStatus(board)

        // then : 게임 상태는  GameStatus.DRAW_GAME를 반환한다.
        assertThat(actual).isEqualTo(GameStatus.DRAW_GAME)
    }
}
