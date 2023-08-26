package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.di.TictactocMapModule
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import com.nextstep.edu.tictactoe.domain.model.Turn

internal class DefaultTictactoeImpl(
    private val strategy: TictactocStrategy
): DefaultTictactoe {

    private val tictactoeMap: TictactoeMap = TictactocMapModule.provideTictactocMap()

    override fun put(point: Point): GameResult = strategy.put(point = point, tictactoeMap = tictactoeMap)

    override fun reset(): Unit = tictactoeMap.resetMap()

    override fun changeTurn(): Unit = tictactoeMap.changeTurn()

    override fun getCurrentTurn(): Turn = tictactoeMap.getCurrentTurn()

    override fun getMap(): Array<Array<Turn>> = tictactoeMap.getMap()
}
