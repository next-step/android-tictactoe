package com.example.tictectoe_domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class GameTest {

    private lateinit var rule: TictectoeRule

    @Before
    fun init() {
        rule = TictectoeRule()
    }

    @Test
    fun `첫 순서에 보드의 중단 오른쪽을 선택하면 해당 위치에 Player1이 차지한다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        val game = Game(Board.EMPTY, rule)

        // when : 보드의 중단 오른쪽을 선택한다.
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)

        // then : 보드의 해당 부분이 player1이 차지한다.
        assertThat(game.getBoard().midRight).isEqualTo(Cell.PLAYER1(Position.MID_RIGHT))
    }

    @Test
    fun `2인 모드에서 두번째 순서에 보드의 중단 가운데를 선택하면 해당 위치에 Player2이 차지한다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        val game = Game(Board.EMPTY, rule)

        // when : 처음엔 중단 오른쪽 두번째는 중단 가운데를 선택한다.
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)
        game.selectBoard(Position.MID_CENTER, GameMode.TWO_PLAYER_MODE)

        // then : 중단 가운데를 player2가 차지한다.
        assertThat(game.getBoard().midCenter).isEqualTo(Cell.PLAYER2(Position.MID_CENTER))
    }

    @Test
    fun `선택됐던 보드를 선택하면 해당 위치에 아무 변화가 없다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        val game = Game(Board.EMPTY, rule)

        // when : 보드의 중단 오른쪽을 두번 선택한다.
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)

        // then : 보드의 해당 부분이 player1이 그대로 입니다.
        assertThat(game.getBoard().midRight).isEqualTo(Cell.PLAYER1(Position.MID_RIGHT))
    }

    @Test
    fun `선택됐던 보드를 선택하면 플레이어 변경이 이뤄지지 않습니다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        //         보드의 중단 오른쪽을 선택하면 playerTurn은 TURN_PLAYER2이다.
        val game = Game(Board.EMPTY, rule)
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)
        assertThat(game.playerTurn).isEqualTo(PlayerTurn.TURN_PLAYER2)

        // when : 처음 놨던 중단 오른쪽을 선택한다.
        game.selectBoard(Position.MID_RIGHT, GameMode.TWO_PLAYER_MODE)

        // then : 플레이어의 턴은 변경되지 않는다.
        assertThat(game.playerTurn).isEqualTo(PlayerTurn.TURN_PLAYER2)
    }

    @Test
    fun `초기 모드는 랜덤 모드로 선택 되어 있다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        val game = Game(Board.EMPTY, rule)

        // then : 게임 모드는 랜덤모드이다.
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM_MODE)
    }

    @Test
    fun `재시작 버튼 클릭 시 보드가 초기화 된다`() {
        // given : 모든 셀이 선택된 보드를 가진 게임을 시작한다.
        val game = Game(Board.TEST_DRAW_BOARD, rule)

        // when : 재시작 로직을 실행한다.
        game.gameReset()

        // then : 공백 보드로 재설정 된다
        assertThat(game.getBoard()).isEqualTo(Board.EMPTY)
    }

    @Test
    fun `재시작을 하더라도 모드는 변경되지 않는다`() {
        // given : 모든 셀이 선택된 보드를 가진 게임을 시작한다.
        //         첫 사직시 게임 모드는 Random모드이다.
        val game = Game(Board.TEST_DRAW_BOARD, rule)
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM_MODE)

        // when : 재시작 로직을 실행한다.
        game.gameReset()

        // then : 게임 모드는 랜덤모드이다.
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM_MODE)
    }

    @Test
    fun `게임모드를 랜덤 모드에서 2인 모드로 변경할 수 있다`() {
        // given : 공백 보드를 가진 게임을 시작한다.
        //         첫 사직시 게임 모드는 Random모드이다.
        val game = Game(Board.EMPTY, rule)

        // when : 게임 모드를 2인 모드로 변경한다.
        game.changeGameMode(GameMode.TWO_PLAYER_MODE)

        // then : 2인 모드로 게임 모드가 변경된다.
        assertThat(game.gameMode).isEqualTo(GameMode.TWO_PLAYER_MODE)
    }
}
