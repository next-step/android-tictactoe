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
    fun `1행에 연속된 X가 3개 존재할 경우 true이다`() {
        // given
        val testCells = listOf(true, true, true, false, false, true, false, true, false)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `2행에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(false, true, false, true, true, true, false, false, true)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `3행에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(false, false, true, false, true, false, true, true, true)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `1열에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(true, false, false, true, true, false, true, false, true)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `2열에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(false, true, false, false, true, true, true, true, false)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `3열에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(false, false, true, true, false, true, false, true, true)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `1행에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(false, false, false, true, true, null, false, true, true)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `2행에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(false, true, true, false, false, false, true, true, null)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `3행에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(true, true, null, false, true, true, false, false, false)

        // when
        val actual = tictactoe.isWinnerForRow(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `1열에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(false, null, true, false, true, true, false, true, false)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `2열에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(true, false, null, true, false, true, false, false, true)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `3열에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(null, true, false, true, true, false, true, false, false)

        // when
        val actual = tictactoe.isWinnerForColumn(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `좌상단에서 우하단으로 이어지는 대각선에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(true, false, false, false, true, true, true, false, true)

        // when
        val actual = tictactoe.isWinnerForLeftToRightDiagonal(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `좌상단에서 우하단으로 이어지는 대각선에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(false, true, null, false, false, true, true, true, false)

        // when
        val actual = tictactoe.isWinnerForLeftToRightDiagonal(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `우상단에서 좌하단으로 이어지는 대각선에 연속된 X가 3개 존재할 경우 X의 true이다`() {
        // given
        val testCells = listOf(false, true, true, false, true, false, true, false, true)

        // when
        val actual = tictactoe.isWinnerForRightToLeftDiagonal(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `우상단에서 좌하단으로 이어지는 대각선에 연속된 O가 3개 존재할 경우 O의 false이다`() {
        // given
        val testCells = listOf(null, true, false, true, false, true, false, false, true)

        // when
        val actual = tictactoe.isWinnerForRightToLeftDiagonal(testCells)

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun `연속된 3개의 마크가 없고 9칸을 모두 사용 했을 경우 true이다`() {
        // given
        val testCells = listOf(true, false, true, true, false, false, false, true, true)

        // when
        val actual = tictactoe.isDraw(testCells)

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun `1행에 연속된 X가 3개 존재할 경우 X의 승리이다`() {
        // given
        val testCells = listOf(true, true, true, false, false, true, false, true, false)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.X)
    }

    @Test
    fun `1행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells = listOf(false, false, false, true, true, null, false, true, true)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.O)
    }

    @Test
    fun `연속된 3개의 마크가 없고 9칸을 모두 사용 했을 경우 무승부다`() {
        // given
        val testCells = listOf(true, false, true, true, false, false, false, true, true)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.DRAW)
    }
}
