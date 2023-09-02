import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.TicTacToe
import camp.nextstep.edu.tictactoe.domain.Turn
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author Daewon on 02,September,2023
 *
 */


class TicTacToeTest {

    private lateinit var ticTacToe: TicTacToe

    @Before
    fun setUp() {
        ticTacToe = TicTacToe()
    }

    @Test
    fun `X가 먼저 시작한다`() {
        // then
        assertThat(ticTacToe.currentTurn()).isEqualTo(Turn.X)
    }

    @Test
    fun `X가 먼저 시작하고, O가 차례가 된다`() {
        // when
        ticTacToe.mark(Position.TOP_CENTER)

        // then
        assertThat(ticTacToe.currentTurn()).isEqualTo(Turn.O)
    }

    @Test
    fun `다시 시작하기를 누르면 초기화 된다`() {
        // given
        ticTacToe.mark(Position.TOP_CENTER)
        ticTacToe.mark(Position.TOP_LEFT)

        // when
        ticTacToe.restart()

        // then
        assertThat(ticTacToe.board).isEqualTo(Board.EMPTY)
    }
}
