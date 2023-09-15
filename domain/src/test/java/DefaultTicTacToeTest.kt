import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.tictactoe.DefaultTicTacToe
import camp.nextstep.edu.tictactoe.domain.Turn
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author Daewon on 02,September,2023
 *
 */


class DefaultTicTacToeTest {

    private lateinit var defaultTicTacToe: DefaultTicTacToe

    @Before
    fun setUp() {
        defaultTicTacToe = DefaultTicTacToe()
    }

    @Test
    fun `X가 먼저 시작한다`() {
        // then
        assertThat(defaultTicTacToe.currentTurn()).isEqualTo(Turn.X)
    }

    @Test
    fun `X가 먼저 시작하고, O가 차례가 된다`() {
        // when
        defaultTicTacToe.mark(Position.TOP_CENTER)

        // then
        assertThat(defaultTicTacToe.currentTurn()).isEqualTo(Turn.O)
    }

    @Test
    fun `다시 시작하기를 누르면 초기화 된다`() {
        // given
        defaultTicTacToe.mark(Position.TOP_CENTER)
        defaultTicTacToe.mark(Position.TOP_LEFT)

        // when
        defaultTicTacToe.restart()

        // then
        assertThat(defaultTicTacToe.getBoard()).isEqualTo(Board.EMPTY)
    }
}
