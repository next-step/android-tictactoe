package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

class RandomMiddleTictactoc(
    randomStrategy: RandomStrategy
) : TictactocStrategy,
    RandomStrategy by randomStrategy {

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
}