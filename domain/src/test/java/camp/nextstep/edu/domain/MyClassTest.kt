package camp.nextstep.edu.domain

import camp.nextstep.edu.tictactoe.domain.GameResult
import camp.nextstep.edu.tictactoe.domain.Point
import camp.nextstep.edu.tictactoe.domain.Ticktacktoe
import camp.nextstep.edu.tictactoe.domain.Turn
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TicktacktoeTest {

    lateinit var ticktacktoe: Ticktacktoe

    @Before
    fun setUp() {
        ticktacktoe = Ticktacktoe()
    }

    @After
    fun tearDown() {
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
    fun `현재 턴에서 한수를 두었을때 가로 한줄이 채워지면 승리한다`() {
        val firstResult = ticktacktoe.isWin(Point(0, 0))
        val secondResult = ticktacktoe.isWin(Point(1, 0))
        val thirdResult = ticktacktoe.isWin(Point(2, 0))

        assertEquals(GameResult.UNKNOWN, firstResult)
        assertEquals(GameResult.UNKNOWN, secondResult)
        assertEquals(GameResult.X_WIN, thirdResult)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 세로 한줄이 채워지면 승리한다`() {
        ticktacktoe.changeTurn()
        val firstResult = ticktacktoe.isWin(Point(0, 0))
        val secondResult = ticktacktoe.isWin(Point(0, 1))
        val thirdResult = ticktacktoe.isWin(Point(0, 2))

        assertEquals(GameResult.UNKNOWN, firstResult)
        assertEquals(GameResult.UNKNOWN, secondResult)
        assertEquals(GameResult.O_WIN, thirdResult)
    }

    @Test
    fun `현재 턴에서 한수를 두었을때 대각선 줄이 채워지면 승리한다`() {
        ticktacktoe.changeTurn()
        val firstResult = ticktacktoe.isWin(Point(0, 0))
        val secondResult = ticktacktoe.isWin(Point(1, 1))
        val thirdResult = ticktacktoe.isWin(Point(2, 2))

        assertEquals(GameResult.UNKNOWN, firstResult)
        assertEquals(GameResult.UNKNOWN, secondResult)
        assertEquals(GameResult.O_WIN, thirdResult)
    }

}