package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

class PlayerTictactoc : TictactocStrategy {

    private val tictactocMap: TictactocMap = TictactocMap()

    override fun put(point: Point): GameResult {
        if (!isValidData(point)) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        return getGameResult(point = point)
    }

    override fun isValidData(point: Point): Boolean {
        return tictactocMap.validData(point = point)
    }

    override fun getGameResult(point: Point): GameResult {
        return tictactocMap.getGameResultFromSetMapPoint(point = point)
    }

    override fun resetMap() = tictactocMap.resetMap()

    override fun changeTurn() = tictactocMap.changeTurn()

    override fun getCurrentTurn(): Turn = tictactocMap.getCurrentTurn()

    override fun getMap(): Array<Array<Turn>> = tictactocMap.getMap()
}