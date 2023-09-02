package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictectoe_domain.Cell
import com.example.tictectoe_domain.TictectoeBoard
import com.example.tictectoe_domain.TictectoeRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TictactoeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var gameBoard: TictectoeBoard
    private lateinit var gameRule: TictectoeRule
    private lateinit var viewModel: TictactoeViewModel

    @Before
    fun setTest() {
        gameBoard = TictectoeBoard()
        gameRule = TictectoeRule()
        viewModel = TictactoeViewModel(gameBoard, gameRule)
    }

    @Test
    fun `처음 보드를 클릭하면 해당 위치의 Board에 첫번째 플레이어가 등록됩니다`() {
        // given :

        // when : 보드를 클릭한다.
        viewModel.clickBoard(1)

        // then : 보드의 해당 위치에 플레이어가 등록된다.
        assertThat(gameBoard.getBoard()[1]).isEqualTo(Cell.PLAYER1)
    }

    @Test
    fun `보드를 두번째 클릭하면 해당 위치의 Board에 두번째 플레이어가 등록됩니다`() {
        // given :

        // when : 보드를 두곳 클릭한다.
        viewModel.clickBoard(1)
        viewModel.clickBoard(2)

        // then : 보드의 해당 위치에 플레이어가 등록된다.
        assertThat(gameBoard.getBoard()[2]).isEqualTo(Cell.PLAYER2)
    }

    @Test
    fun `보드를 선택하면 Player가 변경된다`() {
        // given : 기존 플레이어를 확인한다.
        assertThat(gameBoard.getPlayer()).isEqualTo(Cell.PLAYER1)

        // when : 보드를 한번 클릭한다.
        viewModel.clickBoard(1)

        // then : 변경된 플레이어를 확인한다
        assertThat(gameBoard.getPlayer()).isEqualTo(Cell.PLAYER2)
    }
}
