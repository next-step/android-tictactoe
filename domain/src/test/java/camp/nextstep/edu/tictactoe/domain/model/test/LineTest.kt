package camp.nextstep.edu.tictactoe.domain.model.test

import camp.nextstep.edu.tictactoe.domain.model.Cell
import camp.nextstep.edu.tictactoe.domain.model.Line
import camp.nextstep.edu.tictactoe.domain.model.Position
import com.google.common.truth.Expect
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class LineTest {
    @get:Rule
    val expect: Expect = Expect.create()

    @Test
    fun `모든 셀 중 하나라도 다르면 O, X 둘 다 아니다`() {
        val line = Line.of(
            Cell.X(Position.CellTopLeft),
            Cell.O(Position.CellTop),
            Cell.X(Position.CellTopRight)
        )

        expect.that(line.isO()).isFalse()
        expect.that(line.isX()).isFalse()
    }

    @Test
    fun `모든 셀이 X면 X다`() {
        val line = Line.of(
            Cell.X(Position.CellTopLeft),
            Cell.X(Position.CellTop),
            Cell.X(Position.CellTopRight)
        )

        val actual = line.isX()

        assertThat(actual).isTrue()
    }

    @Test
    fun `모든 셀이 O면 O다`() {
        val line = Line.of(
            Cell.O(Position.CellTopLeft),
            Cell.O(Position.CellTop),
            Cell.O(Position.CellTopRight)
        )

        val actual = line.isO()

        assertThat(actual).isTrue()
    }
}