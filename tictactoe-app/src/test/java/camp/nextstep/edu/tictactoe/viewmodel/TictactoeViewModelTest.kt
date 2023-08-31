package camp.nextstep.edu.tictactoe.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
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
    fun `한 곳 입력 후 상태 체크해보면 진행중으로 나온다`() {
        // given
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val result = viewModel.uiState.value
        // when
        if (result is GameResultUiState.Status) {
            val actual = result.status
            // then
            assertThat(actual).isEqualTo(TictactoeStatus.Progress)
        } else {
            assertThat(result).isInstanceOf(GameResultUiState.Fail::class.java)
        }
    }

    @Test
    fun `한 곳 입력 후 선택한 곳을 또 선택한 상태를 체크해본다`() {
        // when
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val actual = viewModel.uiState.value
        assertThat(actual).isInstanceOf(GameResultUiState.Fail::class.java)
    }
}
