package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

class PlayerTictactoc : TictactocStrategy {

    override fun put(point: Point, tictactocMap: TictactocMap): GameResult {
        val isValidData = tictactocMap.validData(point = point)
        if (!isValidData) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }


        val gameResult = tictactocMap.getGameResultFromSetMapPoint(point = point)
        return gameResult
    }
}