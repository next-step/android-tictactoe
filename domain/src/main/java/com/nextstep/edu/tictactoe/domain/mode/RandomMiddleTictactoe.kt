package com.nextstep.edu.tictactoe.domain.mode

import com.nextstep.edu.tictactoe.domain.RandomStrategy
import com.nextstep.edu.tictactoe.domain.TictactocStrategy
import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import javax.inject.Inject

interface RandomMiddleTictactoe: TictactocStrategy {}


internal class RandomMiddleTictactoeImpl @Inject constructor(
    randomStrategy: RandomStrategy,
    val tictactoeMap: TictactoeMap
) : RandomMiddleTictactoe,
    RandomStrategy by randomStrategy {

    override fun put(point: Point): GameResult {
        if (!isValidData(point = point, tictactoeMap = tictactoeMap)) {
            return if (tictactoeMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = getGameResult(point = point, tictactoeMap = tictactoeMap)

        if (gameResult == GameResult.UNKNOWN) {
            val winPoints = tictactoeMap.getNextPutPointsFromBehavior(behavior = Behavior.WIN)
            val interceptorPoints = tictactoeMap.getNextPutPointsFromBehavior(behavior = Behavior.INTERRUPT)

            if (winPoints.isNotEmpty()) {
                tictactoeMap.changeTurn()
                return tictactoeMap.getGameResultFromSetMapPoint(point = winPoints.first())
            }

            if (interceptorPoints.isNotEmpty()) {
                tictactoeMap.changeTurn()
                return tictactoeMap.getGameResultFromSetMapPoint(point = interceptorPoints.first())
            }

            gameResult = randomPut(point = point, tictactoeMap = tictactoeMap)
        }

        return gameResult
    }
}