package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

fun DefaultTictactoe.fillLeftRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddleLeft)
    return tictactoe.put(Point.CellBottomLeft)
}

fun DefaultTictactoe.fillMiddleRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTop)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottom)
}

fun DefaultTictactoe.fillRightRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddleRight)
    return tictactoe.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillTopColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellTop)
    return tictactoe.put(Point.CellTopRight)
}

fun DefaultTictactoe.fillMiddleColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellMiddleLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellMiddleRight)
}

fun DefaultTictactoe.fillBottomColumnPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellBottomLeft)
    tictactoe.put(Point.CellBottom)
    return tictactoe.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillLeftDiagonalPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillRightDiagonalPoint(): GameResult {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomLeft)
}