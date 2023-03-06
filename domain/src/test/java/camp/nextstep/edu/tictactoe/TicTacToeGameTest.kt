package camp.nextstep.edu.tictactoe

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class TicTacToeGameTest {
    private lateinit var ticTacToeGame: TicTacToeGame

    @Before
    fun setUp() {
        ticTacToeGame = TicTacToeGame()
        ticTacToeGame.isTest = true
    }

    @Test
    fun `(0, 0)에 값 입력 시 해당 칸에는 X가 표시되어야 한다`() {
        // given
        val position = Position(0, 0)

        // when
        ticTacToeGame.putCell(position)

        // then
        val actual = ticTacToeGame.board.getTicTacToeCell(position)
        Truth.assertThat(actual).isEqualTo(OX.X)
    }

    @Test
    fun `(0, 0)에 값이 있는 경우 (0, 1)에 값 입력 시 해당 칸에는 O가 표시되어야 한다`() {
        // given
        val position = Position(0, 0)
        ticTacToeGame.putCell(position)

        // when
        val position2 = Position(0, 1)
        ticTacToeGame.putCell(position2)

        // then
        val actual = ticTacToeGame.board.getTicTacToeCell(position2)
        Truth.assertThat(actual).isEqualTo(OX.O)
    }

    @Test
    fun `(0, 0)에 값이 있는 경우 (0, 0)에 값 입력 시 Expcetion이 발생되어야 한다`() {
        // given
        val position = Position(0, 0)
        ticTacToeGame.putCell(position)

        try {
            // when
            ticTacToeGame.putCell(position)
        } catch (e: IllegalArgumentException) {
            // then
            Truth.assertThat(e.message).isEqualTo("잘못된 위치입니다.")
        }
    }


    @Test
    fun `현재 게임이 진행중이 아니면 Expcetion이 발생되어야 한다`() {
        // given
        ticTacToeGame.putCell(Position(0, 0))
        ticTacToeGame.putCell(Position(1, 1))
        ticTacToeGame.putCell(Position(0, 1))
        ticTacToeGame.putCell(Position(2, 2))
        ticTacToeGame.putCell(Position(0, 2))

        // when
        try {
            // when
            ticTacToeGame.putCell(Position(2, 0))
        } catch (e: IllegalArgumentException) {
            // then
            Truth.assertThat(e.message).isEqualTo("게임이 종료되었습니다.")
        }
    }


    @Test
    fun `랜덤모드에서 사용자가 두는 경우 다음 수는 랜덤으로 진행된다`() {
        // given
        ticTacToeGame = TicTacToeGame(gameMode = GameMode.RANDOM)
        ticTacToeGame.isTest = true

        // when
        val position = Position(0, 0)
        ticTacToeGame.putCell(position)

        // then
        val actual = ticTacToeGame.board.getTicTacToeCell(Position(0, 1))
        Truth.assertThat(actual).isEqualTo(OX.O)
    }
}