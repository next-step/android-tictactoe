package com.example.domain

import org.junit.Test


class EmptyBlockTest {

    @Test
    fun `Player X 를 할당하면 XBlock 을 반환한다`() {
        // given
        val emptyBlock = EmptyBlock()

        // when
        val assignedBlock = emptyBlock.assign(Player.X)

        // then
        assert(assignedBlock is XBlock)
    }

    @Test
    fun `Player O 를 할당하면 OBlock 을 반환한다`() {
        // given
        val emptyBlock = EmptyBlock()

        // when
        val assignedBlock = emptyBlock.assign(Player.O)

        // then
        assert(assignedBlock is OBlock)
    }
}
