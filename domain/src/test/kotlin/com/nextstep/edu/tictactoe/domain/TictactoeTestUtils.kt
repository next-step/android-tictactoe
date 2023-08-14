package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

fun Tictactoe.fillLeftRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddleLeft)
    return tictactoe.put(Point.CellBottomLeft)
}

fun Tictactoe.fillMiddleRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTop)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottom)
}

fun Tictactoe.fillRightRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddleRight)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillTopColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellTop)
    return tictactoe.put(Point.CellTopRight)
}

fun Tictactoe.fillMiddleColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellMiddleLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellMiddleRight)
}

fun Tictactoe.fillBottomColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellBottomLeft)
    tictactoe.put(Point.CellBottom)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillLeftDiagonalPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillRightDiagonalPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomLeft)
}