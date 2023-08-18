package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

fun DefaultTictactoe.fillLeftRowPoint(): GameResult {
    val tictactoe = this
    tictactoe.strategy.put(Point.CellTopLeft)
    tictactoe.strategy.put(Point.CellMiddleLeft)
    return tictactoe.strategy.put(Point.CellBottomLeft)
}

fun DefaultTictactoe.fillMiddleRowPoint(): GameResult {
    strategy.put(Point.CellTop)
    strategy.put(Point.CellMiddle)
    return strategy.put(Point.CellBottom)
}

fun DefaultTictactoe.fillRightRowPoint(): GameResult {
    strategy.put(Point.CellTopRight)
    strategy.put(Point.CellMiddleRight)
    return strategy.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillTopColumnPoint(): GameResult {
    strategy.put(Point.CellTopLeft)
    strategy.put(Point.CellTop)
    return strategy.put(Point.CellTopRight)
}

fun DefaultTictactoe.fillMiddleColumnPoint(): GameResult {
    strategy.put(Point.CellMiddleLeft)
    strategy.put(Point.CellMiddle)
    return strategy.put(Point.CellMiddleRight)
}

fun DefaultTictactoe.fillBottomColumnPoint(): GameResult {
    strategy.put(Point.CellBottomLeft)
    strategy.put(Point.CellBottom)
    return strategy.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillLeftDiagonalPoint(): GameResult {
    strategy.put(Point.CellTopLeft)
    strategy.put(Point.CellMiddle)
    return strategy.put(Point.CellBottomRight)
}

fun DefaultTictactoe.fillRightDiagonalPoint(): GameResult {
    strategy.put(Point.CellTopRight)
    strategy.put(Point.CellMiddle)
    return strategy.put(Point.CellBottomLeft)
}