package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import com.nextstep.edu.tictactoe.domain.model.Turn

internal class TictactoeImpl(
    private val strategy: TictactocStrategy,
    private val tictactoeMap: TictactoeMap
): Tictactoe {

    override fun put(point: Point): GameResult = strategy.put(point = point)

    override fun reset(): Unit = tictactoeMap.resetMap()

    override fun changeTurn(): Unit = tictactoeMap.changeTurn()

    override fun getCurrentTurn(): Turn = tictactoeMap.getCurrentTurn()

    override fun getMap(): Array<Array<Turn>> = tictactoeMap.getMap()
}
