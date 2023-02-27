package com.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


internal class TictactoeTest {
    private lateinit var tictactoe: Tictactoe

    @Before
    fun setUp() {
        tictactoe = Tictactoe()
    }

    @Test
    fun `첫 수는 X의 차례이다`() {
        // when
        val actual = tictactoe.isXTurn()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `두 번째 수는 O의 차례이다`() {
        // given
        tictactoe.isXTurn()

        // when
        val actual = tictactoe.isXTurn()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `세 번째 수는 X의 차례이다`() {
        // given
        tictactoe.isXTurn()
        tictactoe.isXTurn()

        // when
        val actual = tictactoe.isXTurn()

        // then
        assertThat(actual).isTrue()
    }
}