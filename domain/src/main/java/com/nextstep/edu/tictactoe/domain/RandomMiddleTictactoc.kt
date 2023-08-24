package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

class RandomMiddleTictactoc : TictactocStrategy {

    override fun put(point: Point, tictactocMap: TictactocMap): GameResult {
        val isValidData = tictactocMap.validData(point = point)
        if (!isValidData) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = tictactocMap.getGameResultFromSetMapPoint(point = point)

        if (gameResult == GameResult.UNKNOWN) {
            val winPoints = tictactocMap.getNextPutPointsFromBehavior(behavior = Behavior.WIN)
            val interceptorPoints = tictactocMap.getNextPutPointsFromBehavior(behavior = Behavior.INTERRUPT)

            if (winPoints.isNotEmpty()) {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = winPoints.first())
            }

            if (interceptorPoints.isNotEmpty()) {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = winPoints.first())
            }

            gameResult = randomPut(point = point, tictactocMap = tictactocMap)
        }

        return gameResult
    }

    private fun randomPut(point: Point, tictactocMap: TictactocMap): GameResult {
        tictactocMap.changeTurn()
        val range = (0 until DefaultTictactoe.MAP_SIZE)

        var randomPoint = point

        while (!tictactocMap.validData(point = point)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return tictactocMap.getGameResultFromSetMapPoint(point = randomPoint)
    }
}