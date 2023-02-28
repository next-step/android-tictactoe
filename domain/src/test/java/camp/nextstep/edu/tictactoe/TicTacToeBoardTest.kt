package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class TicTacToeBoardTest {
    private lateinit var ticTacToeBoard: TicTacToeBoard

    @Before
    fun setUp() {
        ticTacToeBoard = TicTacToeBoard()
    }

    @Test
    fun `(0, 0)에 값이 있는데 (0, 0)에 값을 넣는 경우 기존에 있던 값이 출력되어야 한다`() {
        // given
        ticTacToeBoard.put(0, 0)
        val actual1 = ticTacToeBoard.getTicTacToeCell(0, 0)

        // when
        ticTacToeBoard.put(0, 0)

        //then
        val actual2 = ticTacToeBoard.getTicTacToeCell(0, 0)
        Truth.assertThat(actual2).isEqualTo(actual1)
    }

    @Test
    fun `(0, 0)에 값을 넣고 (0, 1)에 값을 넣는 각각 X, O가 출력되어야 한다`() {

        // when
        ticTacToeBoard.put(0, 0)
        ticTacToeBoard.put(0, 1)

        //then
        val actual1 = ticTacToeBoard.getTicTacToeCell(0, 0)
        val actual2 = ticTacToeBoard.getTicTacToeCell(0, 1)
        Truth.assertThat(actual1).isEqualTo(OX.X)
        Truth.assertThat(actual2).isEqualTo(OX.O)
    }


    @Test
    fun `값이 있는 게임에 초기화를 하는 경우 모든 셀이 비어있어야 한다`() {
        // given
        val empty = Array<Array<OX?>>(3) { Array(3) { null } }

        ticTacToeBoard.put(0, 0)
        ticTacToeBoard.put(0, 1)
        ticTacToeBoard.put(0, 2)

        ticTacToeBoard.put(1, 0)
        ticTacToeBoard.put(1, 1)
        ticTacToeBoard.put(2, 1)

        ticTacToeBoard.put(1, 2)
        ticTacToeBoard.put(2, 2)
        ticTacToeBoard.put(2, 0)

        // when
        ticTacToeBoard.reset()

        //then
        val actual = ticTacToeBoard.getAllCell()

        Truth.assertThat(actual).isEqualTo(empty)
    }


    @Test
    fun `랜덤모드로 테스트를 진행하는 경우`() {
        // given
        ticTacToeBoard.gameMode = GameMode.RANDOM
        val randomPosition = listOf(RandomInput.getRandomPosition(listOf(0 to 1)))

        // when
        ticTacToeBoard.randomInput(0, 0, randomPosition)

        // then
        val actual = ticTacToeBoard.getTicTacToeCell(0, 1)
        Truth.assertThat(actual).isEqualTo(OX.O)
    }
}