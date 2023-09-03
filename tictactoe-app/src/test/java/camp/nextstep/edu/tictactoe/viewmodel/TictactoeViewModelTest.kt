package camp.nextstep.edu.tictactoe.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
import camp.nextstep.edu.tictactoe.domain.strategy.Mode
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TictactoeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TictactoeViewModel

    @Before
    fun setUp() {
        viewModel = TictactoeViewModel()
    }

    @Test
    fun `2인 플레이 모드에서 X가 한줄을 완성 시키면 X가 승리로 나온다`() {
        // given
        viewModel.changeMode(Mode.TWO_PLAYERS)

        // when
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.BOTTOM_LEFT)
        viewModel.clickCell(CellPosition.TOP)
        viewModel.clickCell(CellPosition.MIDDLE)
        viewModel.clickCell(CellPosition.TOP_RIGHT)

        // then
        val result = viewModel.uiState.value
        assertThat(result).isInstanceOf(GameResultUiState.Status::class.java)
        val actual = (result as? GameResultUiState.Status)?.status
        assertThat(actual).isEqualTo(TictactoeStatus.XWin)
    }

    @Test
    fun `2인 플레이 모드에서 O가 한줄을 완성 시키면 O가 승리로 나온다`() {
        // given
        viewModel.changeMode(Mode.TWO_PLAYERS)

        // when
        viewModel.clickCell(CellPosition.BOTTOM_LEFT)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.MIDDLE)
        viewModel.clickCell(CellPosition.TOP)
        viewModel.clickCell(CellPosition.MIDDLE_RIGHT)
        viewModel.clickCell(CellPosition.TOP_RIGHT)

        // then
        val result = viewModel.uiState.value
        assertThat(result).isInstanceOf(GameResultUiState.Status::class.java)
        val actual = (result as? GameResultUiState.Status)?.status
        assertThat(actual).isEqualTo(TictactoeStatus.OWin)
    }

    @Test
    fun `2인 플레이 모드에서 모두 선택이 되었는데 승자가 나오지 않는다면 무승부로 나온다`() {
        // given
        viewModel.changeMode(Mode.TWO_PLAYERS)

        // when
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.TOP)
        viewModel.clickCell(CellPosition.TOP_RIGHT)
        viewModel.clickCell(CellPosition.MIDDLE)
        viewModel.clickCell(CellPosition.MIDDLE_LEFT)
        viewModel.clickCell(CellPosition.BOTTOM_LEFT)
        viewModel.clickCell(CellPosition.MIDDLE_RIGHT)
        viewModel.clickCell(CellPosition.BOTTOM_RIGHT)
        viewModel.clickCell(CellPosition.BOTTOM)

        // then
        val result = viewModel.uiState.value
        assertThat(result).isInstanceOf(GameResultUiState.Status::class.java)
        val actual = (result as? GameResultUiState.Status)?.status
        assertThat(actual).isEqualTo(TictactoeStatus.Draw)
    }

    @Test
    fun `한 곳 입력 후 상태 체크해보면 진행중으로 나온다`() {
        // given
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val result = viewModel.uiState.value

        // then
        assertThat(result).isInstanceOf(GameResultUiState.Status::class.java)
        val actual = (result as? GameResultUiState.Status)?.status
        assertThat(actual).isEqualTo(TictactoeStatus.Progress)
    }

    @Test
    fun `한 곳 입력 후 선택한 곳을 또 선택한 상태를 체크해본다`() {
        // when
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val actual = viewModel.uiState.value
        // then
        assertThat(actual).isInstanceOf(GameResultUiState.Fail::class.java)
    }

    @Test
    fun `다시 시작하기를 누르면 게임이 초기화 된다`() {
        // when
        viewModel.gameReset()
        // then
        val actual = viewModel.tictactoeMap.value?.positions
        assertThat(actual?.values).doesNotContain(Owner.X)
        assertThat(actual?.values).doesNotContain(Owner.O)
    }

    @Test
    fun `게임 모드 변경을 하는 경우 게임이 초기화 된다`() {
        // when
        viewModel.changeMode(Mode.TWO_PLAYERS)

        // then
        val actual = viewModel.tictactoeMap.value?.positions
        assertThat(actual?.values).doesNotContain(Owner.X)
        assertThat(actual?.values).doesNotContain(Owner.O)
    }
}
