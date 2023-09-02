import camp.nextstep.edu.tictactoe.domain.Board
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.GameStatus
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.Position
import camp.nextstep.edu.tictactoe.domain.TicTacToeManager
import camp.nextstep.edu.tictactoe.domain.test.getDrawBoard
import camp.nextstep.edu.tictactoe.domain.test.getInCompleteBoard
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * @author Daewon on 02,September,2023
 *
 */

class TicTacToeManagerTest {
    private lateinit var ticTacToeManager: TicTacToeManager

    @Before
    fun setUp() {
        ticTacToeManager = TicTacToeManager(Mode.DOUBLE)
    }

    @Test
    fun `행 한줄이 X 표시일 떄, 게임 상태를 가져오면, 게임 종료 상태이다`() {
        // given
        val board = Board(
            mapOf(
                Position.TOP_LEFT to Cell.X(Position.TOP_LEFT),
                Position.TOP_CENTER to Cell.X(Position.TOP_CENTER),
                Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT)
            )
        )

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.WinX)
    }

    @Test
    fun `열 한줄이 O 표시일 때, 게임 상태를 가져오면,  게임 종료 상태이다`() {
        // given
        val board = Board(
            mapOf(
                Position.TOP_CENTER to Cell.O(Position.TOP_CENTER),
                Position.CENTER_CENTER to Cell.O(Position.CENTER_CENTER),
                Position.BOTTOM_CENTER to Cell.O(Position.BOTTOM_CENTER)
            )
        )

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.WinO)
    }

    @Test
    fun `왼쪽에서 오른쪽 대각선 한줄이 X 표시일 때, 게임 상태를 가져오면, 게임 종료 상태이다`() {
        // given
        val board = Board(
            mapOf(
                Position.TOP_LEFT to Cell.X(Position.TOP_LEFT),
                Position.CENTER_CENTER to Cell.X(Position.CENTER_CENTER),
                Position.BOTTOM_RIGHT to Cell.X(Position.BOTTOM_RIGHT)
            )
        )

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.WinX)
    }

    @Test
    fun `오른쪽에서 왼쪽 대각선 한줄이 X 표시일 때, 게임 상태를 가져오면, 게임 종료 상태이다`() {
        // given
        val board = Board(
            mapOf(
                Position.TOP_RIGHT to Cell.X(Position.TOP_RIGHT),
                Position.CENTER_CENTER to Cell.X(Position.CENTER_CENTER),
                Position.BOTTOM_LEFT to Cell.X(Position.BOTTOM_LEFT)
            )
        )

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.WinX)
    }

    @Test
    fun `한 줄도 같은 표기로 채워지지 않았을 때, 게임 상태를 가져오면, 게임 진행 상태이다`() {
        // given
        val board = getInCompleteBoard()

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.InProgress)
    }

    @Test
    fun `모든 줄이 채워졌지만, 같은 표기로 된 줄이 없을 때, 게임 상태를 가져오면, 무승부 상태이다`() {
        // given
        val board = getDrawBoard()

        // when
        val actual = ticTacToeManager.checkGameStatus(board)

        // then
        assertThat(actual).isEqualTo(GameStatus.Draw)
    }



}
