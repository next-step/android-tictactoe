package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class WinningTest {
    private lateinit var board: TicTacToeBoard

    @Before
    fun setUp() {
        board = TicTacToeBoard()
    }

    @Test
    fun `승리조건을 만족하지 못한 경우 null이 나와야한다`() {
        //then
        val actual = Winning.getWinner(board)
        Truth.assertThat(actual).isNull()
    }

    @Test
    fun `행 승리조건 테스트`() {
        // when
        board.put(0, 0)
        board.put(1, 0)
        board.put(0, 1)
        board.put(2, 0)
        board.put(0, 2)


        //then:
        val actual = board.isRow()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `열 승리조건 테스트`() {
        // when
        board.put(0, 0)
        board.put(0, 1)
        board.put(1, 0)
        board.put(0, 2)
        board.put(2, 0)


        //then:
        val actual = board.isColumn()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `대각 승리조건 테스트`() {
        // when
        board.put(0, 0)
        board.put(0, 1)
        board.put(1, 1)
        board.put(0, 2)
        board.put(2, 2)


        //then:
        val actual = board.isDiagonal()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `역대각 승리조건 테스트`() {
        // when
        board.put(0, 2)
        board.put(0, 1)
        board.put(1, 1)
        board.put(0, 0)
        board.put(2, 0)


        //then:
        val actual = board.isReverseDiagonal()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }
}