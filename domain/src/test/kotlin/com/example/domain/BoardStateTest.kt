package com.example.domain

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class BoardStateTest {
    @Test
    fun `길이가 9가 아닌 배열을 주입하면 예외를 던진다`() {
        // given
        val blocks = listOf(
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
            EmptyBlock(),
        )

        // when
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            BoardState(blocks)
        }

        // then
        assertEquals("9개의 블록이 필요합니다.", exception.message)
    }

}
