package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.*
import camp.nextstep.edu.tictactoe.domain.model.Board
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DefaultTicktacktoeTest {

    lateinit var ticktacktoe: Ticktacktoe

    @Before
    fun setUp() {
        ticktacktoe = DefaultTicktacktoe()
    }

    @Test
    fun `X 턴이면 O턴으로 변경 되어야한다`() {
        ticktacktoe.switchTurn()
        val currentTurn = ticktacktoe.currentTurn
        assertThat(Turn.O).isEqualTo(currentTurn)
    }

    @Test
    fun `O 턴이면 X턴으로 변경 되어야한다`() {
        ticktacktoe.switchTurn()
        ticktacktoe.switchTurn()
        val currentTurn = ticktacktoe.currentTurn
        assertThat(Turn.X).isEqualTo(currentTurn)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 세로 한줄이 채워지면 승리한다`() {
        val firstResult = ticktacktoe.put(Position.CellTopLeft)
        val secondResult = ticktacktoe.put(Position.CellMiddleLeft)
        val thirdResult = ticktacktoe.put(Position.CellBottomLeft)

        assertThat(firstResult.first).isEqualTo(State.InProgress)
        assertThat(secondResult.first).isEqualTo(State.InProgress)
        assertThat(thirdResult.first).isEqualTo(State.WinX)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 가로 한줄이 채워지면 승리한다`() {
        ticktacktoe.switchTurn()
        val firstResult = ticktacktoe.put(Position.CellTopLeft)
        val secondResult = ticktacktoe.put(Position.CellMiddleLeft)
        val thirdResult = ticktacktoe.put(Position.CellBottomLeft)

        assertThat(firstResult.first).isEqualTo(State.InProgress)
        assertThat(secondResult.first).isEqualTo(State.InProgress)
        assertThat(thirdResult.first).isEqualTo(State.WinO)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 대각선 줄이 채워지면 승리한다`() {
        ticktacktoe.switchTurn()
        val firstResult = ticktacktoe.put(Position.CellTopLeft)
        val secondResult = ticktacktoe.put(Position.CellMiddle)
        val thirdResult = ticktacktoe.put(Position.CellBottomRight)

        assertThat(firstResult.first).isEqualTo(State.InProgress)
        assertThat(secondResult.first).isEqualTo(State.InProgress)
        assertThat(thirdResult.first).isEqualTo(State.WinO)
    }


    @Test
    fun `처음 시작하면 모든 칸이 비어있어야한다`() {

        //when
        val actual = ticktacktoe.board

        //then
        assertThat(actual).isEqualTo(Board.EMPTY)
    }

    @Test
    fun `X가 왼쪽 위에 마크한다`() {
        ticktacktoe.put(Position.CellTopLeft)
        assertThat(ticktacktoe.board.topLeft).isInstanceOf(Cell.X::class.java)
    }

    @Test
    fun `X가 마크한 뒤에 O가 마크한다`() {
        ticktacktoe.put(Position.CellTopLeft)
        ticktacktoe.switchTurn()
        ticktacktoe.put(Position.CellTop)

        assertThat(ticktacktoe.board.top).isInstanceOf(Cell.O::class.java)
    }

    @Test
    fun `X가 마크한 곳을 O가 마크하면 아무것도 변경되지 않는다`() {
        ticktacktoe.put(Position.CellTopLeft)
        ticktacktoe.switchTurn()
        ticktacktoe.put(Position.CellTopLeft)

        assertThat(ticktacktoe.board.topLeft).isInstanceOf(Cell.X::class.java)
    }
}