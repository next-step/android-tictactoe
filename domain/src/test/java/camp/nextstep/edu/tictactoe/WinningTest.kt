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
        val position1 = Position(0, 0)
        val position2 = Position(1, 0)
        val position3 = Position(2, 0)
        board.put(position1, OX.X)
        board.put(position2, OX.X)
        board.put(position3, OX.X)


        //then
        val actual = board.isRowWin()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `열 승리조건 테스트`() {
        // when
        val position1 = Position(0, 0)
        val position2 = Position(0, 1)
        val position3 = Position(0, 2)
        board.put(position1, OX.X)
        board.put(position2, OX.X)
        board.put(position3, OX.X)


        //then
        val actual = board.isColumnWin()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `대각 승리조건 테스트`() {
        // when
        val position1 = Position(0, 0)
        val position2 = Position(1, 1)
        val position3 = Position(2, 2)
        board.put(position1, OX.X)
        board.put(position2, OX.X)
        board.put(position3, OX.X)


        //then:
        val actual = board.isDiagonalWin()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `역대각 승리조건 테스트`() {
        // when
        val position1 = Position(2, 0)
        val position2 = Position(1, 1)
        val position3 = Position(0, 2)
        board.put(position1, OX.X)
        board.put(position2, OX.X)
        board.put(position3, OX.X)


        //then:
        val actual = board.isReverseDiagonalWin()
        Truth.assertThat(actual).isEqualTo(OX.X)
    }
}