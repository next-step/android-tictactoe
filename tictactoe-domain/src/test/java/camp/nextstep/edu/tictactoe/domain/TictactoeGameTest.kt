package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.strategy.RandomStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TwoPlayersStrategy
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class TictactoeGameTest {
    private lateinit var game: TictactoeGame

    @Before
    fun setUp() {
        game = TictactoeGame()
    }

    @Test
    fun `입력하고 같은자리에 입력하면 에러가 발생한다`() {
        // given
        game.mark(CellPosition.TOP_LEFT)

        // then 에러발생
        assertThrows(IllegalStateException::class.java) {
            // when 같은 자리에 입력
            game.mark(CellPosition.TOP_LEFT)
        }
    }

    @Test
    fun `X 3줄이 완성 되면, X의 승리로 게임이 종료`() {
        // given
        game.mark(CellPosition.TOP_LEFT)
        game.mark(CellPosition.MIDDLE_LEFT)
        game.mark(CellPosition.TOP)
        game.mark(CellPosition.MIDDLE)

        // when X 연속된 3줄 완성
        val actual = game.mark(CellPosition.TOP_RIGHT)
        // then X가 승리
        assertThat(actual).isEqualTo(TictactoeStatus.XWin)
    }

    @Test
    fun `O 3줄이 완성 되면, O의 승리로 게임이 종료`() {
        // given
        game.mark(CellPosition.TOP_LEFT)
        game.mark(CellPosition.MIDDLE_LEFT)
        game.mark(CellPosition.TOP)
        game.mark(CellPosition.MIDDLE)
        game.mark(CellPosition.BOTTOM_RIGHT)

        // when O 연속된 3줄 완성
        val actual = game.mark(CellPosition.MIDDLE_RIGHT)

        // then O가 승리
        assertThat(actual).isEqualTo(TictactoeStatus.OWin)
    }

    @Test
    fun `3줄이 완성 되지 않은 상태이면 상태는 계속 게임 진행상태로 나온다`() {
        // given
        game.mark(CellPosition.TOP_LEFT)

        // when 아직 게임중이라면
        val actual = game.mark(CellPosition.TOP)

        // then 게임 진행중 상태가 나옴
        assertThat(actual).isEqualTo(TictactoeStatus.Progress)
    }

    @Test
    fun `모든 칸이 완성되어도 게임이 끝나지 않으면 무승부`() {
        // given
        game.mark(CellPosition.TOP_LEFT)
        game.mark(CellPosition.TOP)
        game.mark(CellPosition.TOP_RIGHT)
        game.mark(CellPosition.MIDDLE)
        game.mark(CellPosition.MIDDLE_LEFT)
        game.mark(CellPosition.BOTTOM_LEFT)
        game.mark(CellPosition.MIDDLE_RIGHT)
        game.mark(CellPosition.BOTTOM_RIGHT)

        // when 모든 칸이 다 들어간 상태에서 우승자가 나오지 않았다면
        val actual = game.mark(CellPosition.BOTTOM)

        // then 게임은 무승부
        assertThat(actual).isEqualTo(TictactoeStatus.Draw)
    }

    @Test
    fun `X부터 입력한다`() {
        assertThat(game.isXTurn).isEqualTo(true)
    }

    @Test
    fun `X입력 후 O가 입력한다`() {
        game.mark(CellPosition.TOP_LEFT)
        assertThat(game.isXTurn).isEqualTo(false)
    }

    @Test
    fun `다시 시작을 하면 게임이 리셋된다`() {
        game.mark(CellPosition.TOP_LEFT)
        game.gameReset()
        game.tictactoeMap.positions
        val actual = game.tictactoeMap.positions.any {
            it.value != Owner.NONE
        }
        assertThat(actual).isEqualTo(false)
        assertThat(game.isXTurn).isEqualTo(true)
    }

    @Test
    fun `2인 모드에서 게임을 계속하는 경우 진행상태가 null이 온다`() {
        // given
        game = TictactoeGame(TwoPlayersStrategy())

        // when
        val actual = game.continueGame()

        // then
        assertThat(actual).isEqualTo(null)
    }

    @Test
    fun `Random모드에서 게임을 계속하는 경우 진행상태가 진행중으로 온다`() {
        // given
        game = TictactoeGame(RandomStrategy())

        // when
        val actual = game.continueGame()

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.Progress)
    }
}
