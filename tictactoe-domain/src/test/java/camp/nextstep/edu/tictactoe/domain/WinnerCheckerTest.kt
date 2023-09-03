package camp.nextstep.edu.tictactoe.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class WinnerCheckerTest {

    @Test
    fun `X가 연속 3개일 경우 X 승리 체크`() {
        // given
        val positions = mapOf(
            CellPosition.TOP_LEFT to Owner.X,
            CellPosition.TOP to Owner.X,
            CellPosition.TOP_RIGHT to Owner.X

        )
        // when
        val actual = WinnerChecker.check(positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.XWin)
    }

    @Test
    fun `O가 연속 3개일 경우 O 승리 체크`() {
        // given
        val positions = mapOf(
            CellPosition.TOP_LEFT to Owner.O,
            CellPosition.TOP to Owner.O,
            CellPosition.TOP_RIGHT to Owner.O

        )
        val actual = WinnerChecker.check(positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.OWin)
    }

    @Test
    fun `게임이 종료되지 않은 경우 게임 진행 중`() {
        // given
        val positions = mapOf(
            CellPosition.TOP_LEFT to Owner.X,
            CellPosition.TOP to Owner.O
        )
        // when
        val actual = WinnerChecker.check(positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.Progress)
    }

    @Test
    fun `칸을 다 채우고 승자가 없는 경우 무승부`() {
        // given
        val positions = mapOf(
            CellPosition.TOP_LEFT to Owner.X,
            CellPosition.TOP to Owner.O,
            CellPosition.TOP_RIGHT to Owner.X,
            CellPosition.MIDDLE_LEFT to Owner.X,
            CellPosition.MIDDLE to Owner.O,
            CellPosition.MIDDLE_RIGHT to Owner.X,
            CellPosition.BOTTOM_LEFT to Owner.O,
            CellPosition.BOTTOM to Owner.X,
            CellPosition.BOTTOM_RIGHT to Owner.O
        )
        // when
        val actual = WinnerChecker.check(positions)

        // then
        assertThat(actual).isEqualTo(TictactoeStatus.Draw)
    }

    @Test
    fun `주어진 위치 값에서 대상이 다음에 놓을 수 있는 곳이 있다면 해당 포지션을 가져온다`() {
        // given
        val positions = mapOf(
            CellPosition.TOP_LEFT to Owner.X,
            CellPosition.MIDDLE_LEFT to Owner.X,
            CellPosition.TOP_RIGHT to Owner.X,
            CellPosition.BOTTOM_LEFT to Owner.O,
            CellPosition.MIDDLE to Owner.O

        )
        // when
        val actual = WinnerChecker.getPlayerWinPosition(positions, Owner.X)

        // then
        assertThat(actual).isEqualTo(CellPosition.TOP)
    }
}
