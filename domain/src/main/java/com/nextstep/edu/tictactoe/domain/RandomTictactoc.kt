package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

class RandomTictactoc : DefaultTictactoe() {

    override fun put(point: Point): GameResult {
        if (!isValidData(point)) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = getGameResult(point = point)

        if (gameResult == GameResult.UNKNOWN) {
            gameResult = randomPut(point = point)
        }

        return gameResult
    }

    private fun randomPut(point: Point): GameResult {
        changeTurn()
        val range = (0 until MAP_SIZE)

        var randomPoint = point
        while (!isValidData(randomPoint)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint)
    }
}