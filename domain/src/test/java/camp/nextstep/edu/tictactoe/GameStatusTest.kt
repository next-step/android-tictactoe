package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class GameStatusTest {

    private lateinit var ticTacToeBoard: TicTacToeBoard

    @Before
    fun setUp() {
        ticTacToeBoard = TicTacToeBoard()
    }

    @Test
    fun `처음 시작에서 (0, 0)에 값을 넣는 경우 X가 출력되어야 한다`() {
        // when
        ticTacToeBoard.put(0, 0)
        //then
        val actual = ticTacToeBoard.getTicTacToeCell(0, 0)
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `모든 값이 채워져 있고, 이긴 상황이 없는 경우 비겨야 한다`() {
        // given
        ticTacToeBoard.put(0, 0)
        ticTacToeBoard.put(0, 1)
        ticTacToeBoard.put(0, 2)
        ticTacToeBoard.put(1, 0)
        ticTacToeBoard.put(1, 1)
        ticTacToeBoard.put(2, 2)
        ticTacToeBoard.put(2, 1)
        ticTacToeBoard.put(2, 0)
        ticTacToeBoard.put(1, 2)

        // when
        val actual = ticTacToeBoard.getGameStatus()

        //then
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.DRAW)
    }
}