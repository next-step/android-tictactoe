package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import camp.nextstep.edu.tictactoe.domain.Block
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun `0번째 블록 클릭하여 x설정 확인`() {
        viewModel.clickBlock(0)

        val actual = viewModel.blockChecks.value?.get(0)

        Truth.assertThat(actual).isEqualTo(Block.BlockX)
    }

    @Test
    fun `0, 1번째 블록 클릭하여 1번째 블록 O 설정 확인`() {
        viewModel.clickBlock(0)
        viewModel.clickBlock(1)

        val actual = viewModel.blockChecks.value?.get(1)

        Truth.assertThat(actual).isEqualTo(Block.BlockO)
    }

    @Test
    fun `0, 1, 2번째 블록 클릭하여 2번째 블록 X 설정 확인`() {
        viewModel.clickBlock(0)
        viewModel.clickBlock(1)
        viewModel.clickBlock(2)

        val actual = viewModel.blockChecks.value?.get(2)

        Truth.assertThat(actual).isEqualTo(Block.BlockX)
    }

    @Test
    fun `0, 1, 1번째 블록 클릭하여 1번째 블록 O 설정 확인`() {
        viewModel.clickBlock(0)
        viewModel.clickBlock(1)
        viewModel.clickBlock(1)

        val actual = viewModel.blockChecks.value?.get(1)

        Truth.assertThat(actual).isEqualTo(Block.BlockO)
    }

    @Test
    fun `0, 3, 1, 4, 2번째 블록 클릭하여 X 승리 확인`() {
        `블록들을 클릭한다`(listOf(0, 3, 1, 4, 2))

        val actual = viewModel.event.value

        Truth.assertThat(actual).isEqualTo(MainViewModel.Event.EVENT_WIN_X)
    }

    @Test
    fun `0, 3, 1, 4, 2, 5번째 블록 클릭하여 X 승리 확인`() {
        `블록들을 클릭한다`(listOf(0, 3, 1, 4, 2, 5))

        val actual = viewModel.event.value

        Truth.assertThat(actual).isEqualTo(MainViewModel.Event.EVENT_WIN_X)
    }

    @Test
    fun `0, 3, 1, 4, 6, 5번째 블록 클릭하여 O 승리 확인`() {
        `블록들을 클릭한다`(listOf(0, 3, 1, 4, 6, 5))

        val actual = viewModel.event.value

        Truth.assertThat(actual).isEqualTo(MainViewModel.Event.EVENT_WIN_O)
    }

    @Test
    fun `0, 3, 1, 4, 6, 5, 2번째 블록 클릭하여 O 승리 확인`() {
        `블록들을 클릭한다`(listOf(0, 3, 1, 4, 6, 5, 2))

        val actual = viewModel.event.value

        Truth.assertThat(actual).isEqualTo(MainViewModel.Event.EVENT_WIN_O)
    }

    @Test
    fun `모든 블록 클릭하여 무승부 확인`() {
        `블록들을 클릭한다`(listOf(0, 3, 1, 4, 6, 7, 5, 2, 8))

        val actual = viewModel.event.value

        Truth.assertThat(actual).isEqualTo(MainViewModel.Event.EVENT_DRAW)
    }

    private fun `블록들을 클릭한다`(positions: List<Int>) {
        for (position in positions) {
            viewModel.clickBlock(position)
        }
    }
}
