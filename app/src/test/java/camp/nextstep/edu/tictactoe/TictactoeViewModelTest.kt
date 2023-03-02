package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nextstep.edu.tictactoe.domain.Cell
import com.nextstep.edu.tictactoe.domain.Winner
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class TictactoeViewModelTest {
    private lateinit var tictactoeViewModel: TictactoeViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        tictactoeViewModel = TictactoeViewModel()
    }

    @Test
    fun `첫 번째 셀을 선택 했을 때 X가 표시된다`() {
        // given
        val position = 0

        // when
        tictactoeViewModel.mark(position)

        // then
        assertThat(tictactoeViewModel.board[position].value).isEqualTo(Cell.X)
    }

    @Test
    fun `두 번째 셀을 선택 했을 때 O가 표시된다`() {
        // given
        val position0 = 0
        val position1 = 1

        // when
        tictactoeViewModel.mark(position0)
        tictactoeViewModel.mark(position1)

        // then
        assertThat(tictactoeViewModel.board[position1].value).isEqualTo(Cell.O)
    }

    @Test
    fun `1행에 연속된 X가 3개 존재할 경우 X의 승리이다`() {
        // given
        /*
        X X X
          O
            O
        */
        val position0 = 0
        val position1 = 1
        val position2 = 2
        val position4 = 4
        val position8 = 8

        // when
        tictactoeViewModel.mark(position0)
        tictactoeViewModel.mark(position4)
        tictactoeViewModel.mark(position1)
        tictactoeViewModel.mark(position8)
        tictactoeViewModel.mark(position2)

        // then
        assertThat(tictactoeViewModel.onResult.value).isEqualTo(Winner.X)
    }

    @Test
    fun `다시 시작하기를 선택하면 모든 셀은 초기화 된다`() {
        // given
        /*
        X X X
          O
            O
        */
        val position0 = 0
        val position1 = 1
        val position2 = 2
        val position4 = 4
        val position8 = 8
        tictactoeViewModel.mark(position0)
        tictactoeViewModel.mark(position4)
        tictactoeViewModel.mark(position1)
        tictactoeViewModel.mark(position8)
        tictactoeViewModel.mark(position2)

        // when
        tictactoeViewModel.restart()

        // then
        assertThat(tictactoeViewModel.board.all { it.value == Cell.NONE }).isTrue()
    }
}
