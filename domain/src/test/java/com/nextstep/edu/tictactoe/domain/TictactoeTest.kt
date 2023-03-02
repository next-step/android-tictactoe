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
        val actual = tictactoe.toggleTurn()

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `두 번째 수는 O의 차례이다`() {
        // given
        tictactoe.toggleTurn()

        // when
        val actual = tictactoe.toggleTurn()

        // then
        assertThat(actual).isEqualTo(Cell.O)
    }

    @Test
    fun `세 번째 수는 X의 차례이다`() {
        // given
        tictactoe.toggleTurn()
        tictactoe.toggleTurn()

        // when
        val actual = tictactoe.toggleTurn()

        // then
        assertThat(actual).isEqualTo(Cell.X)
    }

    @Test
    fun `1행에 연속된 X가 3개 존재할 경우 승자는 X다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.X, Cell.X, Cell.O, Cell.O, Cell.X, Cell.O, Cell.X, Cell.O)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.X)
    }

    @Test
    fun `1행에 연속된 O가 3개 존재할 경우 승자는 O다`() {
        // given
        val testCells =
            listOf(Cell.O, Cell.O, Cell.O, Cell.X, Cell.X, Cell.NONE, Cell.O, Cell.X, Cell.X)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.O)
    }

    @Test
    fun `연속된 3개의 마크가 없고 9칸을 모두 사용 했을 경우 무승부다`() {
        // given
        val testCells =
            listOf(Cell.X, Cell.O, Cell.X, Cell.X, Cell.O, Cell.O, Cell.O, Cell.X, Cell.X)

        // when
        val actual = tictactoe.findWinner(testCells)

        // then
        assertThat(actual).isEqualTo(Winner.DRAW)
    }

    @Test
    fun `다시 시작하기를 하면 순서가 초기화 된다`() {
        // given
        tictactoe.toggleTurn()

        // when
        tictactoe.restart()

        // then
        assertThat(TurnToggle.isXTurn).isEqualTo(Cell.NONE)
    }
}
