import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Position
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author Daewon on 02,September,2023
 *
 */


class BoardTest {

    private lateinit var board: Board

    @Before
    fun setUp() {
        board = Board.EMPTY
    }

    @Test
    fun `처음 시작하면, 모든 칸이 비어있어야 한다`() {

        // then
        assertThat(board).isEqualTo(Board.EMPTY)
    }

    @Test
    fun `X가 왼쪽 위에 마크한다`() {

        // when
        val actual = board.set(Position.TOP_LEFT, Cell.X(Position.TOP_LEFT))

        // then
        assertThat(actual[Position.TOP_LEFT]).isEqualTo(Cell.X(Position.TOP_LEFT))
    }

    @Test
    fun `X가 마크한 곳을 O가 마크하면 아무것도 변경되지 않고 에러메시지가 출력된다`() {
        // given
        board = board.set(Position.TOP_LEFT, Cell.X(Position.TOP_LEFT))

        // when
        val actual = runCatching { board.set(Position.TOP_LEFT, Cell.O(Position.TOP_LEFT)) }

        // then
        actual.onFailure {
            assertThat(it.message).isEqualTo("이미 마크된 곳입니다.")
        }
    }

    @Test
    fun `이미 마크가 되어 있어도 초기화를 진행하면 비어있어야 한다`() {
        // given
        board.set(Position.TOP_LEFT, Cell.X(Position.TOP_LEFT))
        board.set(Position.TOP_CENTER, Cell.O(Position.TOP_CENTER))

        // when
        board = Board.EMPTY

        // then
        assertThat(board).isEqualTo(Board.EMPTY)
    }
}
