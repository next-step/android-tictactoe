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

    @Test
    fun `1행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(true, true, true, false, false, true, false, true, false)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `2행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(false, true, false, true, true, true, false, false, true)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `3행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(false, false, true, false, true, false, true, true, true)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `1열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(true, false, false, true, true, false, true, false, true)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `2열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(false, true, false, false, true, true, true, true, false)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `3열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells = listOf(false, false, true, true, false, true, false, true, true)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `1행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(false, false, false, true, true, null, false, true, true)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `2행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(false, true, true, false, false, false, true, true, null)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `3행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(true, true, null, false, true, true, false, false, false)

        // when
        val actual = tictactoe.isWinnerRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `1열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(false, null, true, false, true, true, false, true, false)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `2열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(true, false, null, true, false, true, false, false, true)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `3열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(null, true, false, true, true, false, true, false, false)

        // when
        val actual = tictactoe.isWinnerColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }
}
