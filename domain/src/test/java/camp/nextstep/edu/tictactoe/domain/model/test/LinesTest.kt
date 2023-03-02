package camp.nextstep.edu.tictactoe.domain.model.test


import camp.nextstep.edu.tictactoe.domain.model.Cell
import camp.nextstep.edu.tictactoe.domain.model.Line
import camp.nextstep.edu.tictactoe.domain.model.Lines
import camp.nextstep.edu.tictactoe.domain.model.Position
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LinesTest {
    @Test
    fun `한 줄이라도 X면, X다`() {
        val lines = Lines.of(
            Line.of(
                Cell.X(Position.CellTop),
                Cell.X(Position.CellTopLeft),
                Cell.X(Position.CellTopRight)
            ),
            Line.of(
                Cell.Empty(Position.CellMiddleLeft), Cell.Empty(Position.CellMiddle), Cell.Empty(
                    Position.CellMiddleRight
                )
            ),

            )
        assertThat(lines.isX()).isTrue()
    }

    @Test
    fun `한 줄이라도 O면, O다`() {
        val lines = Lines.of(
            Line.of(
                Cell.O(Position.CellTop),
                Cell.O(Position.CellTopLeft),
                Cell.O(Position.CellTopRight)
            ),
            Line.of(
                Cell.Empty(Position.CellMiddleLeft), Cell.Empty(Position.CellMiddle), Cell.Empty(
                    Position.CellMiddleRight
                )
            ),

            )
        assertThat(lines.isO()).isTrue()

    }
}