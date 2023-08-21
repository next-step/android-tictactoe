package camp.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class TictactoeGameTest {

    @Test
    fun `넣은 자리에는 또 넣을 수 없음`() {
        val game = TictactoeGame()
        game.setPosition(true, CellPosition.TOP_LEFT)
        assertThrows(IllegalStateException::class.java) {
            game.setPosition(true, CellPosition.TOP_LEFT)
        }
    }

    @Test
    fun `X 3줄이 완성 되면 게임이 종료`() {
        val game = TictactoeGame()
        game.setPosition(true, CellPosition.TOP_LEFT)
        game.setPosition(true, CellPosition.TOP)
        val actual = game.setPosition(true, CellPosition.TOP_RIGHT)
        assertThat(actual).isEqualTo(GameResult(GameResult.GAME_X_WIN))
    }

    @Test
    fun `O 3줄이 완성 되면 게임이 종료`() {
        val game = TictactoeGame()
        game.setPosition(false, CellPosition.TOP_LEFT)
        game.setPosition(false, CellPosition.TOP)
        val actual = game.setPosition(false, CellPosition.TOP_RIGHT)
        assertThat(actual).isEqualTo(GameResult(GameResult.GAME_O_WIN))
    }

    @Test
    fun `3줄이 완성 되지 않은 상태이면 계속 게임 진행상태`() {
        val game = TictactoeGame()
        game.setPosition(true, CellPosition.TOP_LEFT)
        val actual = game.setPosition(true, CellPosition.TOP)
        assertThat(actual).isEqualTo(GameResult(GameResult.GAME_ING))
    }

    @Test
    fun `모든 칸이 완성되어도 게임이 끝나지 않으면 무승부`() {
        val game = TictactoeGame()
        game.setPosition(true, CellPosition.TOP_LEFT)
        game.setPosition(true, CellPosition.TOP_RIGHT)
        game.setPosition(true, CellPosition.MIDDLE_LEFT)
        game.setPosition(true, CellPosition.MIDDLE_RIGHT)
        game.setPosition(true, CellPosition.BOTTOM)
        game.setPosition(false, CellPosition.TOP)
        game.setPosition(false, CellPosition.MIDDLE)
        game.setPosition(false, CellPosition.BOTTOM_LEFT)
        val actual = game.setPosition(false, CellPosition.BOTTOM_RIGHT)
        assertThat(actual).isEqualTo(GameResult(GameResult.GAME_DRAW))
    }
}
