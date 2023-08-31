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
        val board = mutableListOf(Player.NONE,
            Player.PLAYER1, Player.PLAYER1, Player.PLAYER1,
            Player.NONE, Player.NONE, Player.NONE,
            Player.NONE, Player.NONE, Player.NONE,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player1이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER1)
    }

    @Test
    fun `중단 가로줄 모두 player1이 차지할 경우 승리한 자는 player1이다`() {
        // given : 중단 가로줄 모두 player1인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.NONE, Player.NONE, Player.NONE,
            Player.PLAYER1, Player.PLAYER1, Player.PLAYER1,
            Player.NONE, Player.NONE, Player.NONE,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player1이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER1)
    }

    @Test
    fun `하단 가로줄 모두 player1이 차지할 경우 승리한 자는 player1이다`() {
        // given : 하단 가로줄 모두 player1인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.NONE, Player.NONE, Player.NONE,
            Player.NONE, Player.NONE, Player.NONE,
            Player.PLAYER1, Player.PLAYER1, Player.PLAYER1)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player1이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER1)
    }

    @Test
    fun `좌측 세로줄 모두 player2가 차지할 경우 승리한 자는 player2이다`() {
        // given : 좌측 세로줄 모두 player2인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.PLAYER2, Player.NONE, Player.NONE,
            Player.PLAYER2, Player.NONE, Player.NONE,
            Player.PLAYER2, Player.NONE, Player.NONE,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player2이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `중앙 세로줄 모두 player2가 차지할 경우 승리한 자는 player2이다`() {
        // given : 중앙 세로줄 모두 player2인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.NONE, Player.PLAYER2, Player.NONE,
            Player.NONE, Player.PLAYER2, Player.NONE,
            Player.NONE, Player.PLAYER2, Player.NONE,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player2이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `우측 세로줄 모두 player2가 차지할 경우 승리한 자는 player2이다`() {
        // given : 우측이 모두 player2인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.NONE, Player.NONE, Player.PLAYER2,
            Player.NONE, Player.NONE, Player.PLAYER2,
            Player.NONE, Player.NONE, Player.PLAYER2,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player2이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `우측 상단에서 시작하의 대각선을 모두 player2가 차지할 경우 승리한 자는 player2이다`() {
        // given : 우측 상단에서 시작하는 대각선이 모두 player2인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.PLAYER2, Player.NONE, Player.NONE,
            Player.NONE, Player.PLAYER2, Player.NONE,
            Player.NONE, Player.NONE, Player.PLAYER2,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player2이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `좌측 상단에서 시작하의 대각선을 모두 player2가 차지할 경우 승리한 자는 player2이다`() {
        // given : 좌측 상단에서 시작하는 대각선이 모두 player2인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.NONE, Player.NONE, Player.PLAYER2,
            Player.NONE, Player.PLAYER2, Player.NONE,
            Player.PLAYER2, Player.NONE, Player.NONE,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : Player2이 반환된다.
        assertThat(actual).isEqualTo(Player.PLAYER2)
    }

    @Test
    fun `0번 인덱스를 제외하고 빈 공간이 0개라면 무승부이다`() {
        // given : 승리 조건을 만족하지 않고 빈 공간이 1개인 보드를 만든다.
        val board = mutableListOf(Player.NONE,
            Player.PLAYER2, Player.PLAYER1, Player.PLAYER1,
            Player.PLAYER1, Player.PLAYER2, Player.PLAYER2,
            Player.PLAYER2, Player.PLAYER1, Player.PLAYER1,)

        // when : 게임에 승리 조건에 보드를 넣는다.
        val actual = gameRule.getWinningPlayer(board)

        // then : None이 반환된다.
        assertThat(actual).isEqualTo(Player.NONE)
    }
}
