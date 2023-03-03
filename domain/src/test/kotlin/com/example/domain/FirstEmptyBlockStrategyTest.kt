package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class FirstEmptyBlockStrategyTest {
    @Test
    fun `assign 는 첫번째 빈칸을 반환한다`() {
        // given
        val strategy = FirstEmptyBlockStrategy()
        val boardState = BoardState(
            listOf(
                XBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 1)
    }
}
