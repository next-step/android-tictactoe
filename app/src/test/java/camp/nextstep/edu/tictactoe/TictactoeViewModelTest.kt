package camp.nextstep.edu.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tictectoe_domain.Cell
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.GameMode
import com.example.tictectoe_domain.Position
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TictactoeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TictactoeViewModel

    @Before
    fun setTest() {
        viewModel = TictactoeViewModel(Game())
    }

    @Test
    fun `처음 보드를 클릭하면 해당 위치의 Board에 첫번째 플레이어가 등록됩니다`() {
        // given :

        // when : 상단 좌측 셀을 클릭한다.
        viewModel.clickBoard(Position.TOP_LEFT)

        // then : 보드의 해당 위치에 플레이어가 등록된다.
        viewModel.board.value?.let { board ->
            assertThat(board.topLeft).isEqualTo(Cell.PLAYER1(Position.TOP_LEFT))}
    }

    @Test
    fun `2인 게임 모드에서 보드를 두번째 클릭하면 해당 위치의 Board에 두번째 플레이어가 등록됩니다`() {
        // given :
        viewModel.changeGameMode(GameMode.TWO_PLAYER)

        // when : 보드를 두곳 클릭한다.
        viewModel.clickBoard(Position.TOP_LEFT)
        viewModel.clickBoard(Position.TOP_CENTER)

        // then : 보드의 2번 셀에 PLAYER2가 등록된다.
        viewModel.board.value?.let { board ->
            assertThat(board.topCenter).isEqualTo(Cell.PLAYER2(Position.TOP_CENTER))}

    }
}
