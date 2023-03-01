package com.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

internal class TurnToggleTest {
    @Before
    fun setUp() {
        TurnToggle.restart()
    }

    @Test
    fun `첫 수는 X의 차례이다`() {
        // when
        val actual = TurnToggle.toggleTurn()

        // then
        Truth.assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `두 번째 수는 O의 차례이다`() {
        // given
        TurnToggle.toggleTurn()

        // when
        val actual = TurnToggle.toggleTurn()

        // then
        Truth.assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `세 번째 수는 X의 차례이다`() {
        // given
        TurnToggle.toggleTurn()
        TurnToggle.toggleTurn()

        // when
        val actual = TurnToggle.toggleTurn()

        // then
        Truth.assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `다시 시작하기를 하면 순서가 초기화 된다`() {
        // given
        TurnToggle.toggleTurn()

        // when
        TurnToggle.restart()

        // then
        Truth.assertThat(TurnToggle.isXTurn).isEqualTo(Cell.NONE)
    }
}
