package camp.nextstep.edu.tictactoe.model

import com.nextstep.edu.tictactoe.domain.Tictactoe.Companion.MAP_SIZE
import com.nextstep.edu.tictactoe.domain.model.Turn

data class Board(
    private val map: Array<Array<Turn>>
) {

    fun getBoardRowColumn(
        tictactocCell: TictactocCell
    ): Turn {
        return map[tictactocCell.row][tictactocCell.column]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board

        if (!map.contentDeepEquals(other.map)) return false

        return true
    }

    override fun hashCode(): Int {
        return map.contentDeepHashCode()
    }

    companion object {
        val Empty = Board(
            map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
        )
    }
}

