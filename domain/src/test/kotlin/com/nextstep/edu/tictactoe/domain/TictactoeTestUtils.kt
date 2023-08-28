package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

fun Tictactoe.fillLeftRowPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellMiddleLeft)
    return put(Point.CellBottomLeft)
}

fun Tictactoe.fillMiddleRowPoint(): GameResult {
    put(Point.CellTop)
    put(Point.CellMiddle)
    return put(Point.CellBottom)
}

fun Tictactoe.fillRightRowPoint(): GameResult {
    put(Point.CellTopRight)
    put(Point.CellMiddleRight)
    return put(Point.CellBottomRight)
}

fun Tictactoe.fillTopColumnPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellTop)
    return put(Point.CellTopRight)
}

fun Tictactoe.fillMiddleColumnPoint(): GameResult {
    put(Point.CellMiddleLeft)
    put(Point.CellMiddle)
    return put(Point.CellMiddleRight)
}

fun Tictactoe.fillBottomColumnPoint(): GameResult {
    put(Point.CellBottomLeft)
    put(Point.CellBottom)
    return put(Point.CellBottomRight)
}

fun Tictactoe.fillLeftDiagonalPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellMiddle)
    return put(Point.CellBottomRight)
}

fun Tictactoe.fillRightDiagonalPoint(): GameResult {
    put(Point.CellTopRight)
    put(Point.CellMiddle)
    return put(Point.CellBottomLeft)
}