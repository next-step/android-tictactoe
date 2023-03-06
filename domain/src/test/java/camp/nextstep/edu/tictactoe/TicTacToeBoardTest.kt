package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class TicTacToeBoardTest {
    private lateinit var ticTacToeBoard: TicTacToeBoard

    @Before
    fun setUp() {
        ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.reset()
    }

    @Test
    fun `시작 시 모든 셀이 비어있어야 한다`() {

        // then
        ticTacToeBoard.getAllCell().forEach {
            val actual = it.all { ox -> ox == null }
            Truth.assertThat(actual).isEqualTo(true)
        }
    }

    @Test
    fun `(0, 0)에 X를 입력 시 해당 위치에 X가 들어가야 한다`() {
        // given
        val position = Position(0, 0)
        val turn = OX.X

        // when
        ticTacToeBoard.put(position, turn)

        // then
        val actual = ticTacToeBoard.getTicTacToeCell(position)
        Truth.assertThat(actual).isEqualTo(turn)
    }

    @Test
    fun `모든 셀이 비어있지 않은 경우 isFullBoard의 값이 true가 나와야 한다`() {

        // when
        val turn = OX.X
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                ticTacToeBoard.put(Position(i, j), turn)
            }
        }

        // then
        val actual = ticTacToeBoard.isFullBoard()
        Truth.assertThat(actual).isEqualTo(true)
    }
    
    @Test
    fun `reset 후 모든 셀은 비어있어야 한다`() {
        // given
        val turn = OX.X
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                ticTacToeBoard.put(Position(i, j), turn)
            }
        }
        
        // when
        ticTacToeBoard.reset()
        
        // then
        val actual = ticTacToeBoard.isFullBoard()
        Truth.assertThat(actual).isEqualTo(false)
    }


    @Test
    fun `빈 셀을 탐색 시 true를 반환해야 한다`() {
        // given
        val emptyPosition = Position(0, 0)

        // then
        val actual1 = ticTacToeBoard.isEmptyCell(emptyPosition)
        Truth.assertThat(actual1).isEqualTo(true)

        ticTacToeBoard.put(emptyPosition, OX.X)

        val actual2 = ticTacToeBoard.isEmptyCell(emptyPosition)
        Truth.assertThat(actual2).isEqualTo(false)
    }
}