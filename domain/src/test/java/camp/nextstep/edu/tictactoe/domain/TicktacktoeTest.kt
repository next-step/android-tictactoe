package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.GameResult
import camp.nextstep.edu.tictactoe.domain.model.Point
import camp.nextstep.edu.tictactoe.domain.model.Turn
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TicktacktoeTest {

    lateinit var ticktacktoe: Ticktacktoe

    @Before
    fun setUp() {
        ticktacktoe = Ticktacktoe(Turn.X, GameResultManager())
    }

    @Test
    fun `X 턴이면 O턴으로 변경 되어야한다`() {
        ticktacktoe.changeTurn()
        val currentTurn = ticktacktoe.getCurrentTurn()
        assertEquals(Turn.O, currentTurn)
    }

    @Test
    fun `O 턴이면 X턴으로 변경 되어야한다`() {
        ticktacktoe.changeTurn()
        ticktacktoe.changeTurn()
        val currentTurn = ticktacktoe.getCurrentTurn()
        assertEquals(Turn.X, currentTurn)

    }

    @Test
    fun `현재 턴에서 한수를 두었을때 세로 한줄이 채워지면 승리한다`() {
        val firstResult = ticktacktoe.put(Point.CellTopLeft)
        val secondResult = ticktacktoe.put(Point.CellMiddleLeft)
        val thirdResult = ticktacktoe.put(Point.CellBottomLeft)

        assertEquals(GameResult.UNKNOWN, firstResult.first)
        assertEquals(GameResult.UNKNOWN, secondResult.first)
        assertEquals(GameResult.X_WIN, thirdResult.first)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 가로 한줄이 채워지면 승리한다`() {
        ticktacktoe.changeTurn()
        val firstResult = ticktacktoe.put(Point.CellTopLeft)
        val secondResult = ticktacktoe.put(Point.CellMiddleLeft)
        val thirdResult = ticktacktoe.put(Point.CellBottomLeft)

        assertEquals(GameResult.UNKNOWN, firstResult.first)
        assertEquals(GameResult.UNKNOWN, secondResult.first)
        assertEquals(GameResult.O_WIN, thirdResult.first)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 대각선 줄이 채워지면 승리한다`() {
        ticktacktoe.changeTurn()
        val firstResult = ticktacktoe.put(Point.CellTopLeft)
        val secondResult = ticktacktoe.put(Point.CellMiddle)
        val thirdResult = ticktacktoe.put(Point.CellBottomRight)

        assertEquals(GameResult.UNKNOWN, firstResult.first)
        assertEquals(GameResult.UNKNOWN, secondResult.first)
        assertEquals(GameResult.O_WIN, thirdResult.first)
    }

}