package camp.nextstep.edu.tictactoe.domain

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
        game.setPosition(CellPosition.TOP_LEFT)

        // then 에러발생
        assertThrows(IllegalStateException::class.java) {
            // when 같은 자리에 입력
            game.setPosition(CellPosition.TOP_LEFT)
        }
    }

    @Test
    fun `X 3줄이 완성 되면 게임이 종료`() {
        // given
        game.setPosition(CellPosition.TOP_LEFT)
        game.setPosition(CellPosition.MIDDLE_LEFT)
        game.setPosition(CellPosition.TOP)
        game.setPosition(CellPosition.MIDDLE)

        // when X 연속된 3줄 완성
        val actual = getResult(game.setPosition(CellPosition.TOP_RIGHT))
        // then X가 승리
        assertThat(actual).isEqualTo(GameResult.GAME_X_WIN)
    }

    @Test
    fun `O 3줄이 완성 되면 게임이 종료`() {
        // given
        game.setPosition(CellPosition.TOP_LEFT)
        game.setPosition(CellPosition.MIDDLE_LEFT)
        game.setPosition(CellPosition.TOP)
        game.setPosition(CellPosition.MIDDLE)
        game.setPosition(CellPosition.BOTTOM_RIGHT)

        // when O 연속된 3줄 완성
        val actual = getResult(game.setPosition(CellPosition.MIDDLE_RIGHT))

        // then O가 승리
        assertThat(actual).isEqualTo(GameResult.GAME_O_WIN)
    }

    @Test
    fun `3줄이 완성 되지 않은 상태이면 계속 게임 진행상태`() {
        // given
        game.setPosition(CellPosition.TOP_LEFT)

        // when 아직 게임중이라면
        val actual = getResult(game.setPosition(CellPosition.TOP))

        // then 게임 진행중 상태가 나옴
        assertThat(actual).isEqualTo(GameResult.GAME_ING)
    }

    @Test
    fun `모든 칸이 완성되어도 게임이 끝나지 않으면 무승부`() {
        // given
        game.setPosition(CellPosition.TOP_LEFT)
        game.setPosition(CellPosition.TOP)
        game.setPosition(CellPosition.TOP_RIGHT)
        game.setPosition(CellPosition.MIDDLE)
        game.setPosition(CellPosition.MIDDLE_LEFT)
        game.setPosition(CellPosition.BOTTOM_LEFT)
        game.setPosition(CellPosition.MIDDLE_RIGHT)
        game.setPosition(CellPosition.BOTTOM_RIGHT)
        // when 모든 칸이 다 들어간 상태에서 우승자가 나오지 않았다면
        val actual = getResult(game.setPosition(CellPosition.BOTTOM))

        // then 게임은 무승부
        assertThat(actual).isEqualTo(GameResult.GAME_DRAW)
    }

    @Test
    fun `X부터 입력한다`() {
        assertThat(game.isXTurn).isEqualTo(true)
    }

    @Test
    fun `X입력 후 O가 입력한다`() {
        game.setPosition(CellPosition.TOP_LEFT)
        assertThat(game.isXTurn).isEqualTo(false)
    }

    @Test
    fun `다시 시작을 하면 게임이 리셋된다`() {
        game.setPosition(CellPosition.TOP_LEFT)
        game.gameReset()
        val actual = game.tictactoeMap.positions.any {
            it.value != Owner.NONE
        }
        assertThat(actual).isEqualTo(false)
        assertThat(game.isXTurn).isEqualTo(true)
    }

    private fun getResult(result: GameResult<Int>): Any {
        val actual = when (result) {
            is GameResult.GameStatus -> result.result
            else -> {}
        }
        return actual
    }
}
