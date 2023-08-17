package camp.nextstep.edu.tictactoe.model

import com.nextstep.edu.tictactoe.domain.Tictactoe.Companion.MAP_SIZE
import com.nextstep.edu.tictactoe.domain.model.Turn

sealed class Board {

    abstract val map: Array<Array<Turn>>

    class Empty(
        override val map: Array<Array<Turn>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
    ): Board()

    class NotEmpty(
        override val map: Array<Array<Turn>>
    ): Board()

    fun getBoardRowColumn(
        tictactocCell: TictactocCell
    ): Turn {
        return map[tictactocCell.row][tictactocCell.column]
    }
}
