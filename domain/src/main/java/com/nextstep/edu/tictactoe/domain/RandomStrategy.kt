package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.Tictactoe.Companion.MAP_SIZE
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import javax.inject.Inject

interface RandomStrategy {
    fun randomPut(point: Point, tictactoeMap: TictactoeMap): GameResult
    fun isValidData(point: Point, tictactoeMap: TictactoeMap): Boolean
    fun getGameResult(point: Point, tictactoeMap: TictactoeMap): GameResult
}

internal class DefaultRandomStrategy @Inject constructor(): RandomStrategy {

    override fun randomPut(point: Point, tictactoeMap: TictactoeMap): GameResult {
        tictactoeMap.changeTurn()
        val range = (0 until MAP_SIZE)

        var randomPoint = point
        while (!isValidData(point = randomPoint, tictactoeMap = tictactoeMap)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint, tictactoeMap = tictactoeMap)
    }

    override fun isValidData(point: Point, tictactoeMap: TictactoeMap): Boolean {
        return tictactoeMap.validData(point = point)
    }

    override fun getGameResult(point: Point, tictactoeMap: TictactoeMap): GameResult {
        return tictactoeMap.getGameResultFromSetMapPoint(point = point)
    }

}