package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val instantTaskExecution = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
    }


    @Test
    fun `입력된 값이 없을 때 (0, 0)에 값 입력 시 (0, 0)에 X가 입력되어야 한다`() {
        // when
        mainViewModel.putCell(0, 0)

        // then
        val actual = mainViewModel.cellsLiveData.getOrAwaitValue()
        Truth.assertThat(actual[0][0]).isEqualTo(OX.X)
    }

    @Test
    fun `(0, 0)에 값이 있을 때 (0, 1)에 값 입력 시 (0, 1)에 O가 입력되어야 한다`() {
        // given
        mainViewModel.putCell(0, 0)

        // when
        mainViewModel.putCell(0, 1)

        // then
        val actual = mainViewModel.cellsLiveData.getOrAwaitValue()
        Truth.assertThat(actual[0][1]).isEqualTo(OX.O)
    }

    @Test
    fun `(0, 0)에 값이 있을 때 (0, 0)에 값 입력 시 (0, 0)의 값은 변경되지 않아야 한다`() {
        // given
        mainViewModel.putCell(0, 0)
        val actual1 = mainViewModel.cellsLiveData.getOrAwaitValue()

        // when
        mainViewModel.putCell(0, 0)

        // then
        val actual = mainViewModel.cellsLiveData.getOrAwaitValue()
        val actual2 = mainViewModel.gameStatus.getOrAwaitValue()

        Truth.assertThat(actual[0][0]).isEqualTo(actual1[0][0])
        Truth.assertThat(actual2).isEqualTo(TicTacToeStatus.PLAYING)
    }

    @Test
    fun `tictactoe 값이 존재할 때 다시하기 한 경우 빈 tictactoe 배열이 나와야 한다`() {
        val initValue = TicTacToe().getAllCell()
        // given
        mainViewModel.putCell(0, 0)
        mainViewModel.putCell(0, 1)

        // when
        mainViewModel.reset()

        // then
        val actual = mainViewModel.cellsLiveData.getOrAwaitValue()
        val actual1 = mainViewModel.gameStatus.getOrAwaitValue()

        Truth.assertThat(actual).isEqualTo(initValue)
        Truth.assertThat(actual1).isEqualTo(TicTacToeStatus.PLAYING)
    }

    @Test
    fun `X가 이긴 경우 'X 승리' 메세지가 출력되어야 한다`() {
        // when
        mainViewModel.putCell(0, 0)
        mainViewModel.putCell(0, 1)
        mainViewModel.putCell(1, 0)
        mainViewModel.putCell(0, 2)
        mainViewModel.putCell(2, 0)

        // then
        val actual = mainViewModel.gameStatus.getOrAwaitValue()
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.X_WIN)
    }

    @Test
    fun `모든 데이터가 채워진 경우 '무승부' 메세지가 출력되어야 한다`() {
        // given
        mainViewModel.putCell(0, 0)
        mainViewModel.putCell(0, 1)
        mainViewModel.putCell(0, 2)
        mainViewModel.putCell(1, 0)
        mainViewModel.putCell(1, 1)
        mainViewModel.putCell(2, 0)
        mainViewModel.putCell(1, 2)
        mainViewModel.putCell(2, 2)

        // when
        mainViewModel.putCell(2, 1)

        // then
        val actual = mainViewModel.gameStatus.getOrAwaitValue()
        Truth.assertThat(actual).isEqualTo(TicTacToeStatus.DRAW)
    }
}