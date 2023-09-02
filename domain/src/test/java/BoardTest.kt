import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.TicTacToe
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

}
