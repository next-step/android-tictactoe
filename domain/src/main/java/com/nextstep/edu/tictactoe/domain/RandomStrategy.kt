package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

interface RandomStrategy {
    fun randomPut(point: Point, tictactocMap: TictactocMap): GameResult
    fun isValidData(point: Point, tictactocMap: TictactocMap): Boolean
    fun getGameResult(point: Point, tictactocMap: TictactocMap): GameResult
}

class DefaultRandomStrategy: RandomStrategy {

    override fun randomPut(point: Point, tictactocMap: TictactocMap): GameResult {
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

    override fun isValidData(point: Point, tictactocMap: TictactocMap): Boolean {
        return tictactocMap.validData(point = point)
    }

    override fun getGameResult(point: Point, tictactocMap: TictactocMap): GameResult {
        return tictactocMap.getGameResultFromSetMapPoint(point = point)
    }

}