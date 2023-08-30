package camp.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class WinnerCheckerTest {

    @Test
    fun `X가 연속 3개일 경우 X 승리 체크`() {
        // given
        val tictactoeMap = TictactoeMap()
        tictactoeMap.set(CellPosition.TOP_LEFT, true)
        tictactoeMap.set(CellPosition.TOP, true)
        tictactoeMap.set(CellPosition.TOP_RIGHT, true)

        // when
        val actual = WinnerChecker.check(tictactoeMap.positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.XWin)
    }

    @Test
    fun `O가 연속 3개일 경우 O 승리 체크`() {
        // given
        val tictactoeMap = TictactoeMap()
        tictactoeMap.set(CellPosition.TOP_LEFT, false)
        tictactoeMap.set(CellPosition.TOP, false)
        tictactoeMap.set(CellPosition.TOP_RIGHT, false)

        // when
        val actual = WinnerChecker.check(tictactoeMap.positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.OWin)
    }

    @Test
    fun `게임이 종료되지 않은 경우 게임 진행 중`() {
        // given
        val tictactoeMap = TictactoeMap()
        tictactoeMap.set(CellPosition.TOP_LEFT, false)
        tictactoeMap.set(CellPosition.TOP, false)

        // when
        val actual = WinnerChecker.check(tictactoeMap.positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.Progress)
    }

    @Test
    fun `칸을 다 채우고 승자가 없는 경우 무승부`() {
        // given
        val tictactoeMap = TictactoeMap()
        tictactoeMap.set(CellPosition.TOP_LEFT, true)
        tictactoeMap.set(CellPosition.TOP, false)
        tictactoeMap.set(CellPosition.TOP_RIGHT, true)
        tictactoeMap.set(CellPosition.MIDDLE_LEFT, true)
        tictactoeMap.set(CellPosition.MIDDLE, false)
        tictactoeMap.set(CellPosition.MIDDLE_RIGHT, true)
        tictactoeMap.set(CellPosition.BOTTOM_LEFT, false)
        tictactoeMap.set(CellPosition.BOTTOM, true)
        tictactoeMap.set(CellPosition.BOTTOM_RIGHT, false)

        // when
        val actual = WinnerChecker.check(tictactoeMap.positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.Draw)
    }
}
