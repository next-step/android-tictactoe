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
    fun `상대방이 대각선 2개 코너를 가지는 경우 비어있는 첫 사이드 중앙에 둔다`() {
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
                XBlock
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 1)
    }

    @Test
    fun `상대방만 유일하게 코너를 하나가지고 있으면 반대편에 둔다`() {
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
                XBlock,
                EmptyBlock()
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
                OBlock,
                OBlock,
                XBlock,
                XBlock,
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
    fun `위의 모든 조건이 충족하지 않을 땐 남는 곳 중 가장 빠른곳에 둔다`() {
        val strategy = DrawBlockStrategy()
        val boardState = BoardState(
            listOf(
                XBlock,
                OBlock,
                XBlock,
                EmptyBlock(),
                OBlock,
                EmptyBlock(),
                XBlock,
                XBlock,
                OBlock
            )
        )

        // when
        val result = strategy.calculateNextDoing(boardState)

        // then
        assertEquals(result, 3)
    }
}
