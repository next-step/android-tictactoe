package camp.nextstep.edu.tictactoe.domain


import camp.nextstep.edu.tictactoe.domain.model.Position
import camp.nextstep.edu.tictactoe.domain.model.State
import camp.nextstep.edu.tictactoe.domain.model.TurnResult
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class AiTicktacktoeTest {
    private lateinit var ticktacktoe: Ticktacktoe
    private val points =
        listOf(
            Position.CellTopLeft, Position.CellTop, Position.CellTopRight,
            Position.CellMiddleLeft, Position.CellMiddle, Position.CellMiddleLeft,
            Position.CellBottomLeft, Position.CellMiddle, Position.CellBottomLeft
        )

    @Before
    fun setUp() {
        ticktacktoe = AiTicktacktoe()
    }

    @Test
    fun `Ai는 유저가 선택한 point를 제외하고 선택한 후 turn 결과를 반환한다`() {
        val expectedPoints = points.filter { it != Position.CellTopLeft }
        val result = ticktacktoe.runOneTurn(Position.CellTopLeft)
        assertThat(result.cells.size).isEqualTo(2)
        assertThat(result.state).isEqualTo(State.InProgress)
        //user 선택
        assertThat(result.cells[TurnResult.KEY_USER]?.position).isEqualTo(Position.CellTopLeft)
        //ai 선택
        assertThat(expectedPoints.contains(result.cells[TurnResult.KEY_AI]?.position)).isTrue()
    }

}
