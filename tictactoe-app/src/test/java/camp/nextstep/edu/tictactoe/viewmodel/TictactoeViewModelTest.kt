package camp.nextstep.edu.tictactoe.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class TictactoeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TictactoeViewModel

    @Test
    fun `X부터 입력한다`() {
        val tictactoeGame = TictactoeGame()
        viewModel = TictactoeViewModel(tictactoeGame)
        assertThat(viewModel.isXturn).isEqualTo(true)
    }

    @Test
    fun `X입력 후 O가 입력한다`() {
        val tictactoeGame = TictactoeGame()
        viewModel = TictactoeViewModel(tictactoeGame)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        assertThat(viewModel.isXturn).isEqualTo(false)
    }

    @Test
    fun `다시 시작을 하면 게임이 리셋된다`() {
        val tictactoeGame = TictactoeGame()
        viewModel = TictactoeViewModel(tictactoeGame)
        viewModel.clickCell(CellPosition.TOP_LEFT)
        viewModel.gameReset()
        assertThat(viewModel.tictactoeMap.value?.isEmpty()).isEqualTo(true)
        assertThat(viewModel.isXturn).isEqualTo(true)
    }
}
