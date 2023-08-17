package camp.nextstep.edu.tictactoe.model

import com.nextstep.edu.tictactoe.domain.model.Point

enum class TictactocCell(
    val row: Int,
    val column: Int
) {
    CellTopLeft(0, 0),
    CellTop(0, 1),
    CellTopRight(0, 2),
    CellMiddleLeft(1, 0),
    CellMiddle(1, 1),
    CellMiddleRight(1, 2),
    CellBottomLeft(2, 0),
    CellBottom(2, 1),
    CellBottomRight(2, 2);

    companion object {
        fun toPoint(tictactocCell: TictactocCell): Point {
            return when (tictactocCell) {
                CellTopLeft -> Point.CellTopLeft
                CellTop -> Point.CellTop
                CellTopRight -> Point.CellTopRight
                CellMiddleLeft -> Point.CellMiddleLeft
                CellMiddle -> Point.CellMiddle
                CellMiddleRight -> Point.CellMiddleRight
                CellBottomLeft -> Point.CellBottomLeft
                CellBottom -> Point.CellBottom
                CellBottomRight -> Point.CellBottomRight
            }
        }
    }
}