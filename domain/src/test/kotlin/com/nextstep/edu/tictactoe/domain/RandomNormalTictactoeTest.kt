package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.di.TictactocModule
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Turn
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RandomNormalTictactoeTest {

    lateinit var tictactoe: Tictactoe

    @Before
    fun setUp() {
        tictactoe = TictactocModule.provideRandomNormalTictactoc()
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
}