package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

class RandomTictactoc : TictactocStrategy {

    private val tictactocMap: TictactocMap = TictactocMap()

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

    private fun randomPut(point: Point): GameResult {
        tictactocMap.changeTurn()
        val range = (0 until DefaultTictactoe.MAP_SIZE)

        var randomPoint = point
        while (!isValidData(randomPoint)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint)
    }
}