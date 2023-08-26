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

class TictactoeMap {

    var positions = initPositions()
    fun set(position: CellPosition, isXTurn: Boolean) {
        check(positions[position] == Owner.NONE) {
            "선택한 곳에 또 입력할 수 없음"
        }
        positions[position] = if (isXTurn) {
            Owner.X
        } else {
            Owner.O
        }
    }

    fun isAllOccupied(): Boolean {
        return positions.none {
            it.value == Owner.NONE
        }
    }

    private fun initPositions(): MutableMap<CellPosition, Owner> {
        return CellPosition.values().associateWith {
            Owner.NONE
        }.toMutableMap()
    }

    fun checkWin(): GameResult<Int> {
        WIN_SET.forEach {
            if (checkWin(Owner.X, it)) {
                return GameResult.GameStatus(GameResult.GAME_X_WIN)
            }
            if (checkWin(Owner.O, it)) {
                return GameResult.GameStatus(GameResult.GAME_O_WIN)
            }
        }
        return GameResult.GameStatus(GameResult.GAME_ING)
    }

    private fun checkWin(turn: Owner, winList: List<CellPosition>) =
        positions.filter { (_, owner) ->
            owner == turn
        }.map { (position, _) ->
            position
        }.containsAll(winList)

    companion object {
        val WIN_SET = listOf(
            listOf(TOP_LEFT, TOP, TOP_RIGHT),
            listOf(MIDDLE_LEFT, MIDDLE, MIDDLE_RIGHT),
            listOf(BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT),
            listOf(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT),
            listOf(TOP, MIDDLE, BOTTOM),
            listOf(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT),
            listOf(TOP_LEFT, MIDDLE, BOTTOM_RIGHT),
            listOf(TOP_RIGHT, MIDDLE, BOTTOM_LEFT)
        )
    }
}
