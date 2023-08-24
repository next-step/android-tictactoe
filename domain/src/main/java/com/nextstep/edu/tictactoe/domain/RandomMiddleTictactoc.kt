package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

class RandomMiddleTictactoc : TictactocStrategy {

    override fun put(point: Point, tictactocMap: TictactocMap): GameResult {
        if (!isValidData(point = point, tictactocMap = tictactocMap)) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = getGameResult(point = point, tictactocMap = tictactocMap)

        if (gameResult == GameResult.UNKNOWN) {
            val winPoints = tictactocMap.getNextPutPointsFromBehavior(behavior = Behavior.WIN)
            val interceptorPoints = tictactocMap.getNextPutPointsFromBehavior(behavior = Behavior.INTERRUPT)

            if (winPoints.isNotEmpty()) {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = winPoints.first())
            }

            if (interceptorPoints.isNotEmpty()) {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = interceptorPoints.first())
            }

            gameResult = randomPut(point = point, tictactocMap = tictactocMap)
        }

        return gameResult
    }

    private fun randomPut(point: Point, tictactocMap: TictactocMap): GameResult {
        tictactocMap.changeTurn()
        val range = (0 until DefaultTictactoe.MAP_SIZE)

        var randomPoint = point
        while (!isValidData(point = randomPoint, tictactocMap = tictactocMap)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint, tictactocMap = tictactocMap)
    }

    private fun isValidData(point: Point, tictactocMap: TictactocMap): Boolean {
        return tictactocMap.validData(point = point)
    }

    private fun getGameResult(point: Point, tictactocMap: TictactocMap): GameResult {
        return tictactocMap.getGameResultFromSetMapPoint(point = point)
    }
}