package com.example.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertThrows
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
class TurnTest {

    @RunWith(value = Parameterized::class)
    class OddTurnTest(private val turn: Int) {
        @Test
        fun `홀수는 Player O의 턴이다`() {
            // given
            val turn = Turn(turn)

            // when
            val player = turn.whoseTurn()

            // then
            assertEquals(Player.O, player)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}턴은 Player O의 턴이다")
            fun numbers(): List<Array<Any>> {
                return listOf(
                    arrayOf(1),
                    arrayOf(3),
                    arrayOf(5),
                    arrayOf(7),
                    arrayOf(9),
                )
            }
        }
    }

    @RunWith(value = Parameterized::class)
    class EvenTurnTest(private val turn: Int) {
        @Test
        fun `0을 포함한 짝수는 Player X의 턴이다`() {
            // given
            val turn = Turn(turn)

            // when
            val player = turn.whoseTurn()

            // then
            assertEquals(Player.X, player)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}턴은 Player X의 턴이다")
            fun numbers(): List<Array<Any>> {
                return listOf(
                    arrayOf(0),
                    arrayOf(2),
                    arrayOf(4),
                    arrayOf(6),
                    arrayOf(8),
                )
            }
        }
    }

    class NonParameterizedTest {
        @Test
        fun `0미만의 턴은 존재할 수 없다`() {
            // given
            val turn = -1

            // when
            val exception = assertThrows(IllegalArgumentException::class.java) {
                Turn(turn)
            }
            // then
            assertEquals("턴은 0보다 작을 수 없습니다.", exception.message)
        }
    }
}
