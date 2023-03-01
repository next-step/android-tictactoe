package com.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class WinnerSelectorTest {
    @Test
    fun `1행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.X, Cell.X, Cell.O, Cell.O, Cell.X, Cell.O, Cell.X, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `2행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.X, Cell.O, Cell.X, Cell.X, Cell.X, Cell.O, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `3행에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.O, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X, Cell.X, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `1열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.O, Cell.O, Cell.X, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `2열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.X, Cell.O, Cell.O, Cell.X, Cell.X, Cell.X, Cell.X, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `3열에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.O, Cell.X, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `1행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.O, Cell.O, Cell.X, Cell.X, Cell.NONE, Cell.O, Cell.X, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `2행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.X, Cell.X, Cell.O, Cell.O, Cell.O, Cell.X, Cell.X, Cell.NONE)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `3행에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.X, Cell.NONE, Cell.O, Cell.X, Cell.X, Cell.O, Cell.O, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForRow(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `1열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.NONE, Cell.X, Cell.O, Cell.X, Cell.X, Cell.O, Cell.X, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `2열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.O, Cell.NONE, Cell.X, Cell.O, Cell.X, Cell.O, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `3열에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.NONE, Cell.X, Cell.O, Cell.X, Cell.X, Cell.O, Cell.X, Cell.O, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForColumn(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `좌상단에서 우하단으로 이어지는 대각선에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.O, Cell.O, Cell.O, Cell.X, Cell.X, Cell.X, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForLeftToRightDiagonal(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `좌상단에서 우하단으로 이어지는 대각선에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.X, Cell.NONE, Cell.O, Cell.O, Cell.X, Cell.X, Cell.X, Cell.O)

        // when
        val actual = WinnerSelector.findWinnerForLeftToRightDiagonal(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `우상단에서 좌하단으로 이어지는 대각선에 연속된 X가 3개 존재할 경우 X의 승리다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.X, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForRightToLeftDiagonal(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `우상단에서 좌하단으로 이어지는 대각선에 연속된 O가 3개 존재할 경우 O의 승리다`() {
        // given
        val testCells =
            listOf(Cell.NONE, Cell.X, Cell.O, Cell.X, Cell.O, Cell.X, Cell.O, Cell.O, Cell.X)

        // when
        val actual = WinnerSelector.findWinnerForRightToLeftDiagonal(testCells)

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `연속된 3개의 마크가 없고 9칸을 모두 사용 했을 경우 무승부이다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.O, Cell.X, Cell.X, Cell.O, Cell.O, Cell.O, Cell.X, Cell.X)

        // when
        val actual = WinnerSelector.isDraw(testCells)

        // then
        assertThat(actual).isTrue()
    }
}
