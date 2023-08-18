package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

abstract class DefaultTictactoe: TictactocPut {

    protected val tictactocMap: TictactocMap = TictactocMap()

    fun getMap(): Array<Array<Turn>> = tictactocMap.getMap()

    fun getCurrentTurn(): Turn = tictactocMap.getCurrentTurn()

    protected fun isValidData(point: Point): Boolean = tictactocMap.validData(point = point)

    protected fun getGameResult(point: Point): GameResult = tictactocMap.getGameResultFromSetMapPoint(point = point)

    fun reset() = tictactocMap.resetMap()

    fun changeTurn() = tictactocMap.changeTurn()

    companion object {
        const val MAP_SIZE = 3
    }
}
