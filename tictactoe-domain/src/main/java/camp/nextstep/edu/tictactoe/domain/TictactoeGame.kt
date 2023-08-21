package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM
import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM_RIGHT
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP_RIGHT
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE_RIGHT
import java.lang.IllegalStateException

class TictactoeGame {

    private val tictactoeMap = CellPosition.values().toMutableSet()

    private var xPositions = listOf<CellPosition>()
    private var oPositions = listOf<CellPosition>()

    fun setPosition(isXTurn: Boolean, position: CellPosition) : GameResult {
        if(tictactoeMap.contains(position)) {
            tictactoeMap.remove(position)
            if(isXTurn) {
                xPositions = xPositions + position
            } else {
                oPositions = oPositions + position
            }
        } else {
            throw IllegalStateException("같은 자리에 또 넣을 수 없음")
        }
        return checkGameResult()
    }

    private fun checkGameResult(): GameResult {
        WIN_SET.forEach {
            if(xPositions.containsAll(it)) {
                return GameResult(GameResult.GAME_X_WIN)
            }
            if(oPositions.containsAll(it)) {
                return GameResult(GameResult.GAME_O_WIN)
            }
        }
        if(xPositions.size == GAME_DRAW_SIZE) {
            return GameResult(GameResult.GAME_DRAW)
        }
        return GameResult(GameResult.GAME_ING)
    }

    companion object {
        const val GAME_DRAW_SIZE = 5
        val WIN_SET = listOf(
            listOf(TOP_LEFT, TOP, TOP_RIGHT),
            listOf(MIDDLE_LEFT, MIDDLE, MIDDLE_RIGHT),
            listOf(BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT),
            listOf(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
            listOf(TOP, MIDDLE, BOTTOM),
            listOf(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT),
            listOf(TOP_LEFT, MIDDLE, BOTTOM_RIGHT),
            listOf(TOP_RIGHT, MIDDLE, BOTTOM_LEFT),
        )
    }
}