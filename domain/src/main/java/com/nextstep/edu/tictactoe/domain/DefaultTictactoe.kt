package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap

class DefaultTictactoe(
    val strategy: TictactocStrategy
) {

    private val tictactocMap: TictactocMap = TictactocMap()

    fun put(point: Point) = strategy.put(point = point, tictactocMap = tictactocMap)

    fun reset() =  tictactocMap.resetMap()

    fun changeTurn() = tictactocMap.changeTurn()

    fun getCurrentTurn() = tictactocMap.getCurrentTurn()

    fun getMap() = tictactocMap.getMap()

    companion object {
        const val MAP_SIZE = 3
    }
}
