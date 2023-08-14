package camp.nextstep.edu.tictactoe.model

import com.nextstep.edu.tictactoe.domain.model.Point

enum class TictactocCell {
    CellTopLeft,
    CellTop,
    CellTopRight,
    CellMiddleLeft,
    CellMiddle,
    CellMiddleRight,
    CellBottomLeft,
    CellBottom,
    CellBottomRight;

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