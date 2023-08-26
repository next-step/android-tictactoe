package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

class DefaultTictactoe(
    private val strategy: TictactocStrategy
) {

    private val tictactocMap: TictactocMap = TictactocMap()

    fun put(point: Point): GameResult = strategy.put(point = point, tictactocMap = tictactocMap)

    fun reset(): Unit = tictactocMap.resetMap()

    fun changeTurn(): Unit = tictactocMap.changeTurn()

    fun getCurrentTurn(): Turn = tictactocMap.getCurrentTurn()

    fun getMap(): Array<Array<Turn>> = tictactocMap.getMap()

    companion object {
        const val MAP_SIZE = 3
    }
}
