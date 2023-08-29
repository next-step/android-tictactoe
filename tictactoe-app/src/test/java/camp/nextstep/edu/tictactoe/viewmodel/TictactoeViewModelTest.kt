package camp.nextstep.edu.tictactoe.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TictactoeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TictactoeViewModel
    private lateinit var tictactoeGame: TictactoeGame

    @Before
    fun setUp() {
        tictactoeGame = TictactoeGame()
        viewModel = TictactoeViewModel(tictactoeGame)
    }

    @Test
    fun `한 곳 입력 후 상태를 체크해본다`() {
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val actual = viewModel.uiState.value
        assertThat(actual).isInstanceOf(GameResult.Status::class.java)
    }

    @Test
    fun `한 곳 입력 후 선택한 곳을 또 선택한 상태를 체크해본다`() {
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        val actual = viewModel.uiState.value
        assertThat(actual).isInstanceOf(GameResult.Fail::class.java)
    }
}
