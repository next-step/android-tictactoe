package camp.nextstep.edu.tictactoe.domain.model.test

import camp.nextstep.edu.tictactoe.domain.model.Board
import com.google.common.truth.Truth.assertThat
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Test
import kotlin.random.Random

class BoardTest {

    //9개의 칸을 만들지 않으면 예외를 발생 시킨다.
    @Test
    fun `9개 칸에 빈 셀을 만든다`() {
        Board.EMPTY
    }

    @Test
    fun `랜덤 한 숫자가 잘 나오는지 확인하다`() {
        mockkObject(Random)
        every { Random.nextInt(any(), any()) } returns 5

        val board = Board.EMPTY
        assertThat(board.getRandomCell()).isEqualTo(board.middleRight)
        clearMocks(Random)
    }
}
