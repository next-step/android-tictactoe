package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class DrawBlockStrategyTest {

    @Test
    fun `중앙이 비어있으면 중앙을 최우선으로 둔다`() {
        // given
        val strategy = DrawBlockStrategy()
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
        assertEquals(result, 4)
    }

    @Test
    fun `적과 AI가 이기는 곳이 있으면 AI가 이기는 곳을 우선으로 둔다`() {
        // given
        val strategy = DrawBlockStrategy()
        val boardState = BoardState(
            listOf(
                XBlock,
                EmptyBlock(),
                OBlock,
                XBlock,
                OBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                XBlock
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 6)
    }

    @Test
    fun `AI가 이기는 곳이 없을 때 상대방이 이기는 곳을 막는다`() {
        val strategy = DrawBlockStrategy()
        val boardState = BoardState(
            listOf(
                EmptyBlock(),
                XBlock,
                OBlock,
                EmptyBlock(),
                OBlock,
                EmptyBlock(),
                XBlock,
                XBlock,
                EmptyBlock(),
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 8)
    }

    @Test
    fun `상대방이 이기는 곳이 없으면 빈 코너 중 빠른 곳을 우선으로 둔다`() {
        // given
        val strategy = DrawBlockStrategy()
        val boardState = BoardState(
            listOf(
                XBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                OBlock,
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock(),
                EmptyBlock()
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 2)
    }

    @Test
    fun `남는 곳에 둔다`() {
    }

}
