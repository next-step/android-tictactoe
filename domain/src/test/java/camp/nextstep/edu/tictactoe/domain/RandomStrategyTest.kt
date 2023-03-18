package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Board
import camp.nextstep.edu.tictactoe.domain.model.Position
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.*
import io.mockk.mockk

class RandomStrategyTest {

    private lateinit var randomStrategy: RandomStrategy

    @Before
    fun setUp() {
        randomStrategy = RandomStrategy()
    }

    @Test
    fun `board의 getRandomCell을 호출하고 position으로 출력한다`() {
        val board = mockk<Board>(relaxed = true)
        val position =randomStrategy.getAiPosition(board)
        assertThat(position).isInstanceOf(Position::class.java)
        verify {
            board.getRandomCell()
        }
    }
}
