package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

fun DefaultTictactoe.fillLeftRowPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellMiddleLeft)
    return put(Point.CellBottomLeft)
}

fun DefaultTictactoe.fillMiddleRowPoint(): GameResult {
    put(Point.CellTop)
    put(Point.CellMiddle)
    return put(Point.CellBottom)
}

fun DefaultTictactoe.fillRightRowPoint(): GameResult {
    put(Point.CellTopRight)
    put(Point.CellMiddleRight)
    return put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillTopColumnPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellTop)
    return put(Point.CellTopRight)
}

fun DefaultTictactoe.fillMiddleColumnPoint(): GameResult {
    put(Point.CellMiddleLeft)
    put(Point.CellMiddle)
    return put(Point.CellMiddleRight)
}

fun DefaultTictactoe.fillBottomColumnPoint(): GameResult {
    put(Point.CellBottomLeft)
    put(Point.CellBottom)
    return put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillLeftDiagonalPoint(): GameResult {
    put(Point.CellTopLeft)
    put(Point.CellMiddle)
    return put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillRightDiagonalPoint(): GameResult {
    put(Point.CellTopRight)
    put(Point.CellMiddle)
    return put(Point.CellBottomLeft)
}