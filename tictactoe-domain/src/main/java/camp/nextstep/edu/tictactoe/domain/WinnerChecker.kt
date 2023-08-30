package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM
import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.BOTTOM_RIGHT
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.MIDDLE_RIGHT
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP_LEFT
import camp.nextstep.edu.tictactoe.domain.CellPosition.TOP_RIGHT

object WinnerChecker {

    private const val CELL_POSITIONS = 9

    private val WIN_SET = listOf(
        listOf(TOP_LEFT, TOP, TOP_RIGHT),
        listOf(MIDDLE_LEFT, MIDDLE, MIDDLE_RIGHT),
        listOf(BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT),
        listOf(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
        listOf(TOP, MIDDLE, BOTTOM),
        listOf(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT),
        listOf(TOP_LEFT, MIDDLE, BOTTOM_RIGHT),
        listOf(TOP_RIGHT, MIDDLE, BOTTOM_LEFT)
    )

    fun check(positions: Map<CellPosition, Owner>): TictactoeStatus {
        WIN_SET.forEach {
            if (getMarkedPositions(Owner.X, positions).containsAll(it)) {
                return TictactoeStatus.XWin
            }
            if (getMarkedPositions(Owner.O, positions).containsAll(it)) {
                return TictactoeStatus.OWin
            }
        }

        val isEmpty = positions.none {
            it.value == Owner.NONE
        }
        if (positions.size == CELL_POSITIONS && isEmpty) {
            return TictactoeStatus.Draw
        }

        return TictactoeStatus.Progress
    }

    private fun getMarkedPositions(turn: Owner, positions: Map<CellPosition, Owner>) =
        positions.filter { (_, owner) ->
            owner == turn
        }.map { (position, _) ->
            position
        }
}
