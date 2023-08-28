package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoeImpl
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMapImpl
import com.nextstep.edu.tictactoe.domain.model.Turn
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RandomMiddleTictactoeTest {

    lateinit var tictactoe: Tictactoe

    @Before
    fun setUp() {
        val tictactoeMap = TictactoeMapImpl()
        tictactoe = TictactoeImpl(RandomMiddleTictactoeImpl(randomStrategy = DefaultRandomStrategy(), tictactoeMap), tictactoeMap)
    }

    @Test
    fun `X가 선택을 하면 O도 자동으로 선택한다`() {
        // given: X턴으로 시작한다.

        // when: X가 보드에서 선택을 했다.
        tictactoe.put(Point.CellTopLeft)

        // then: O도 자동으로 선택을 했다.
        var actual = 0
        tictactoe.getMap().forEach { lines ->
            lines.forEach { turn ->
                if (turn == Turn.O) actual += 1
            }
        }
        assertEquals(1, actual)
    }

    @Test
    fun `X가 TOP에 두개를 선택 하면 O는 남은 TOP를 막는다`() {
        var actual = Turn.UNKNOWN

        while (actual != Turn.O) {
            // given: X턴으로 시작한다.
            tictactoe.reset()

            // when: X가 보드에서 TOP을 두개 선택하면
            tictactoe.put(Point.CellTopLeft)
            tictactoe.put(Point.CellTop)

            // then: O는 남은 TOP을 막는다.
            actual = tictactoe.getMap()[0][2]
        }
        assertEquals(Turn.O, actual)
    }

    @Test
    fun `X가 LEFT에 두개를 선택 하면 O는 남은 LEFT BOTTOM를 막는다`() {
        var actual = Turn.UNKNOWN

        while (actual != Turn.O) {
            // given: X턴으로 시작한다.
            tictactoe.reset()

            // when: X가 보드에서 LEFT을 두개 선택하면
            tictactoe.put(Point.CellTopLeft)
            tictactoe.put(Point.CellMiddleLeft)

            // then: O는 남은 LEFT을 막는다.
            actual = tictactoe.getMap()[2][0]
        }
        assertEquals(Turn.O, actual)
    }

    @Test
    fun `X가 대각선에 두개를 선택 하면 O는 남은 대각선를 막는다`() {
        var actual = Turn.UNKNOWN

        while (actual != Turn.O) {
            // given: X턴으로 시작한다.
            tictactoe.reset()

            // when: X가 보드에서 대각선을 두개 선택하면
            tictactoe.put(Point.CellTopLeft)
            tictactoe.put(Point.CellMiddle)

            // then: O는 남은 대각선을 막는다.
            actual = tictactoe.getMap()[2][2]
        }
        assertEquals(Turn.O, actual)
    }
}