package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Board
import camp.nextstep.edu.tictactoe.domain.model.Cell
import camp.nextstep.edu.tictactoe.domain.model.Position
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DrawStrategyTest {

    private lateinit var drawStrategy: DrawStrategy
    private lateinit var board: Board

    @Before
    fun setUp() {
        board = Board.EMPTY
        drawStrategy = DrawStrategy()
    }

    @Test
    fun `중앙이 비어있으면 중앙에 둔다`() {
        val position = drawStrategy.getAiPosition(board)
        assertThat(position).isEqualTo(Position.CellMiddle)
    }

    @Test
    fun `내가 이기는 수가 있으면 이기는 수를 둔다`() {
        board = board.mark(Cell.X(Position.CellMiddle)).mark(Cell.O(Position.CellTopLeft))
            .mark(Cell.O(Position.CellTop))

        val position = drawStrategy.getAiPosition(board)
        assertThat(position).isEqualTo(Position.CellTopRight)
    }

    @Test
    fun `사이드를 막는다`() {
        board = board.mark(Cell.O(Position.CellMiddle)).mark(Cell.X(Position.CellTopLeft))
            .mark(Cell.X(Position.CellBottomRight))
        val position = drawStrategy.getAiPosition(board)
        val expectedSidePositions = listOf(
            Position.CellTop,
            Position.CellMiddleLeft,
            Position.CellMiddleRight,
            Position.CellBottom
        )
        assertThat(position).isIn(expectedSidePositions)
    }


    @Test
    fun `상대가 가진 코너가 1개인 경우에 반대편에 둔다`() {
        board = board.mark(Cell.O(Position.CellMiddle)).mark(Cell.X(Position.CellTopLeft))
        val position = drawStrategy.getAiPosition(board)
        assertThat(position).isEqualTo(Position.CellBottomRight)
    }

    @Test
    fun `코너에 둔다`() {
        board = board.mark(Cell.O(Position.CellMiddle)).mark(Cell.X(Position.CellTopLeft))
           .mark(Cell.O(Position.CellBottomLeft))
        val position = drawStrategy.getAiPosition(board)
        val expectedCornerPositions = listOf(
            Position.CellTopLeft,
            Position.CellTopRight,
            Position.CellBottomLeft,
            Position.CellBottomRight
        )
        assertThat(position).isIn(expectedCornerPositions)
    }

}
