package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Status

fun Tictactoe.fillLeftRowPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddleLeft)
    return tictactoe.put(Point.CellBottomLeft)
}

fun Tictactoe.fillMiddleRowPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTop)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottom)
}

fun Tictactoe.fillRightRowPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddleRight)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillTopColumnPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellTop)
    return tictactoe.put(Point.CellTopRight)
}

fun Tictactoe.fillMiddleColumnPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellMiddleLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellMiddleRight)
}

fun Tictactoe.fillBottomColumnPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellBottomLeft)
    tictactoe.put(Point.CellBottom)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillLeftDiagonalPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTopLeft)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomRight)
}

fun Tictactoe.fillRightDiagonalPoint(): Pair<GameResult, Status> {
    val tictactoe = this
    tictactoe.put(Point.CellTopRight)
    tictactoe.put(Point.CellMiddle)
    return tictactoe.put(Point.CellBottomLeft)
}