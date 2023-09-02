import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.TicTacToe
import camp.nextstep.edu.tictactoe.domain.Turn
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * @author Daewon on 02,September,2023
 *
 */


class BoardTest {

    @Test
    fun `처음 시작하면, 모든 칸이 비어있어야 한다`() {
        // when
        val ticTacToe = TicTacToe()

        // then
        assertThat(ticTacToe.board).isEqualTo(Board.EMPTY)
    }

    @Test
    fun `X가 왼쪽 위에 마크한다`() {
        // given
        val ticTacToe = TicTacToe()

        // when
        ticTacToe.mark(Position.TOP_LEFT, Turn.X)

        // then
        assertThat(ticTacToe.board[Position.TOP_LEFT]).isEqualTo(Cell.X(Position.TOP_LEFT))

    }

    @Test
    fun `X가 마크한 곳을 O가 마크하면 아무것도 변경되지 않고 에러메시지가 출력된다`() {
        // given
        val ticTacToe = TicTacToe()
        ticTacToe.mark(Position.TOP_LEFT, Turn.X)

        // when
        val actual = ticTacToe.mark(Position.TOP_LEFT, Turn.O)

        // then
        actual.onFailure {
            assertThat(it.message).isEqualTo("이미 마크된 곳입니다.")
        }
    }
}
