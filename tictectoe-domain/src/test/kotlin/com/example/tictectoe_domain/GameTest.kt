package com.example.tictectoe_domain

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
        assertThat(board.tictectoeBoard[1]).isEqualTo(Cell.PLAYER1)
    }

    @Test
    fun `2인 모드에서 두번째 순서에 보드를 선택하면 해당 위치에 Player2이 차지한다`() {
        // given :게임 모드를 2인 모드로 변경한다.
        game.changeTwoPlayerMode()

        // when : 보드에서 각각 다른 위치를 선택한다.
        game.selectBoard(1)
        game.selectBoard(2)

        // then : 보드의 해당 부분이 player2로 바뀝니다.
        assertThat(board.tictectoeBoard[2]).isEqualTo(Cell.PLAYER2)
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
        assertThat(board.tictectoeBoard[1]).isEqualTo(Cell.PLAYER1)
    }

    @Test
    fun `초기 모드는 랜덤 모드로 선택 되어 있다`() {
        // then :
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM)
    }

    @Test
    fun `랜덤 모드에서 사용자가 1번 셀을 선택하면 다음은 자동으로 2번을 선택한다`() {
        // given :
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM)

        // when : 사용자가 1번 셀을 선택한다.
        game.selectBoard(1, 2)

        // then : 자동으로 2번 셀을 셀이 선택한다.
        assertThat(game.getBoard()[2]).isEqualTo(Cell.PLAYER2)
    }

    @Test
    fun `재시작 버튼 클릭 시 보드가 초기화 된다`() {
        // given : 1번 셀을 선택해둔다. 랜덤하게 하나의 셀이 추가로 선택된다.
        game.selectBoard(1)

        // when : 재시작 로직을 실행한다.
        game.gameReset()

        // then : 보드에 Cell.NONE이 10개가 된다. == 초기화가 된다.
        assertThat(game.getBoard().count{it == Cell.NONE}).isEqualTo(10)
    }

    @Test
    fun `재시작을 하더라도 모드는 변경되지 않는다`() {
        // given : 초기 게임모드는 랜덤이다.
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM)

        // when : 재시작 로직을 실행한다.
        game.gameReset()

        // then : 게임모드는 여전히 랜덤이다.
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM)
    }

    @Test
    fun `게임모드를 랜덤 모드에서 2인 모드로 변경할 수 있다`() {
        // given : 초기 게임모드는 랜덤이다.
        assertThat(game.gameMode).isEqualTo(GameMode.RANDOM)

        // when : 게임 모드를 2인 모드로 변경한다.
        game.changeTwoPlayerMode()

        // then : 2인 모드로 게임 모드가 변경된다.
        assertThat(game.gameMode).isEqualTo(GameMode.TWO_PLAYER)
    }
}
