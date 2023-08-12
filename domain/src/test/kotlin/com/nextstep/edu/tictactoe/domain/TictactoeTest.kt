package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Turn
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TictactoeTest {

    lateinit var tictactoe: Tictactoe

    @Before
    fun setUp() {
        tictactoe = Tictactoe(Turn.X, GameResultManager())
    }

    @Test
    fun `X 턴이면 O턴으로 변경 되어야한다`() {
        // given: X턴으로 시작한다.

        // when: 턴을 변경한다.
        tictactoe.changeTurn()

        // then: O의 턴으로 변경된다.
        val actual = tictactoe.getCurrentTurn()
        assertEquals(Turn.O, actual)
    }

    @Test
    fun `O 턴이면 X턴으로 변경 되어야한다`() {
        // given: X턴으로 시작한다.

        // when: 턴을 두번 변경한다.
        tictactoe.changeTurn()
        tictactoe.changeTurn()

        // then: X의 턴으로 변경된다.
        val actual = tictactoe.getCurrentTurn()
        assertEquals(Turn.X, actual)
    }

    @Test
    fun `X의 턴일 때 하나만 클릭 되어 있으면 결과는 아직 모른다`() {
        // given: X의 턴일 때

        // when: 세로줄이 체워진다.
        val actual = tictactoe.put(Point.CellBottomLeft)

        // then: 아직 모른다.
        assertEquals(GameResult.UNKNOWN, actual.first)
    }

    @Test
    fun `X의 턴일 때 세로 줄이 채워지면 승리한다`() {
        // given: X의 턴일 때

        // when: 세로줄이 체워진다.
        val actual = tictactoe.fillTopColumnPoint()

        // then: X가 승리한다.
        assertEquals(GameResult.X_WIN, actual.first)
    }

    @Test
    fun `O의 턴일 때 세로 줄이 채워지면 승리한다`() {
        // given: O의 턴일 때
        tictactoe.changeTurn()

        // when: 세로줄이 체워진다.
        val actual = tictactoe.fillTopColumnPoint()

        // then: O가 승리한다.
        assertEquals(GameResult.O_WIN, actual.first)
    }

    @Test
    fun `X의 턴일 때 가로 줄이 채워지면 O가 승리한다`() {
        // given: X의 턴일 때,

        // when: 가로줄이 채워진다.
        val actual = tictactoe.fillLeftRowPoint()

        // then: X가 승리한다.
        assertEquals(GameResult.X_WIN, actual.first)
    }

    @Test
    fun `O의 턴일 때 가로 줄이 채워지면 O가 승리한다`() {
        // given: O의 턴일 때,
        tictactoe.changeTurn()

        // when: 가로줄이 채워진다.
        val actual = tictactoe.fillLeftRowPoint()

        // then: O가 승리한다.
        assertEquals(GameResult.O_WIN, actual.first)
    }

    @Test
    fun `X의 턴일 때 대각선 줄이 채워지면 승리한다`() {
        // given: X의 턴일 때

        // when: 대각선이 채워진다.
        val actual = tictactoe.fillLeftDiagonalPoint()

        // then: X가 승리한다.
        assertEquals(GameResult.X_WIN, actual.first)
    }

    @Test
    fun `O의 턴일 때 대각선 줄이 채워지면 승리한다`() {
        // given: O의 턴일 때
        tictactoe.changeTurn()

        // when: 대각선이 채워진다.
        val actual = tictactoe.fillRightDiagonalPoint()

        // then: O가 승리한다.
        assertEquals(GameResult.O_WIN, actual.first)
    }

}