package com.nextstep.edu.tictactoe.domain.mode

import com.nextstep.edu.tictactoe.domain.TictactocStrategy
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import javax.inject.Inject

interface PlayerTictactoe : TictactocStrategy {}


internal class PlayerTictactoeImpl @Inject constructor(
    val tictactoeMap: TictactoeMap
) : PlayerTictactoe {

    override fun put(point: Point): GameResult {
        val isValidData = tictactoeMap.validData(point = point)
        if (!isValidData) {
            return if (tictactoeMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        val gameResult = tictactoeMap.getGameResultFromSetMapPoint(point = point)
        return gameResult
    }
}