package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

abstract class Tictactoe {

    abstract fun put(point: Point): GameResult

    private val gameResultManager = GameResultManager()
    private val tictactocMap: TictactocMap = TictactocMap()
    private var currentTurn: Turn = Turn.X

    protected var isFinish: Boolean = false

    fun getMap(): Array<Array<Turn>> {
        return tictactocMap.getMap()
    }

    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    protected fun isValidData(point: Point): Boolean {
        return tictactocMap.validData(point = point, isFinish = isFinish)
    }

    protected fun getGameResult(point: Point): GameResult {
        tictactocMap.setMapRowColumn(row = point.row, column = point.column, turn = currentTurn)
        val gameResult = gameResultManager.getTurnResult(point, tictactocMap.getMap(), currentTurn)
        isFinish = gameResult != GameResult.UNKNOWN
        return gameResult
    }

    fun reset() {
        isFinish = false
        currentTurn = Turn.X
        tictactocMap.resetMap()
    }

    fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O
        else Turn.X
    }

    companion object {
        const val MAP_SIZE = 3
    }
}
