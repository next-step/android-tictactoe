package com.example.domain

import org.hamcrest.CoreMatchers.isA
import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
class TurnTest {

    @RunWith(value = Parameterized::class)
    class OddTurnTest(private val turn: Turn) {
        @Test
        fun `홀수는 Player O의 턴이다`() {
            // when
            val player = turn.whoseTurn()

            // then
            assertEquals(Player.O, player)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}턴은 Player O의 턴이다")
            fun numbers(): List<Array<Any?>> {
                return listOf(
                    arrayOf(1, null),
                    arrayOf(3, null),
                    arrayOf(5, null),
                    arrayOf(7, null),
                )
            }
        }
    }

    @RunWith(value = Parameterized::class)
    class EvenTurnTest(private val turn: Turn) {
        @Test
        fun `0을 포함한 짝수는 Player X의 턴이다`() {
            // when
            val player = turn.whoseTurn()

            // then
            assertEquals(Player.X, player)
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}턴은 Player X의 턴이다")
            fun numbers(): List<Array<Any?>> {
                return listOf(
                    arrayOf(0, null),
                    arrayOf(2, null),
                    arrayOf(4, null),
                    arrayOf(6, null),
                    arrayOf(8, null),
                )
            }
        }
    }

    @RunWith(value = Parameterized::class)
    class TurnConstructorTest(private val turn: Turn) {
        @Test
        fun `생성 테스트`() {
            // then
            assertTrue(isA(Turn::class.java).matches(turn))
        }

        companion object {
            @JvmStatic
            @Parameterized.Parameters(name = "{0}로 턴을 생성할 수 있다.")
            fun numbers(): List<Array<Any?>> {
                return listOf(
                    arrayOf(0, null),
                    arrayOf(1, null),
                    arrayOf(2, null),
                    arrayOf(3, null),
                    arrayOf(4, null),
                    arrayOf(5, null),
                    arrayOf(6, null),
                    arrayOf(7, null),
                    arrayOf(8, null)
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
            assertEquals("턴은 0보다 작거나, 8보다 클 수 없습니다.", exception.message)
        }

        @Test
        fun `9이상의 턴은 존재할 수 없다`() {
            // given
            val turn = 9

            // when
            val exception = assertThrows(IllegalArgumentException::class.java) {
                Turn(turn)
            }
            // then
            assertEquals("턴은 0보다 작거나, 8보다 클 수 없습니다.", exception.message)
        }
    }
}
