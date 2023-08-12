package com.nextstep.edu.tictactoe.domain.model

enum class Point(
    val row: Int,
    val column: Int,
) {
    CellTopLeft(0, 0),
    CellTop(0, 1),
    CellTopRight(0, 2),
    CellMiddleLeft(1, 0),
    CellMiddle(1, 1),
    CellMiddleRight(1, 2),
    CellBottomLeft(2, 0),
    CellBottom(2, 1),
    CellBottomRight(2, 2)
}
