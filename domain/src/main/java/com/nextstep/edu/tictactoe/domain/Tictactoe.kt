package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Turn

class Tictactoe constructor(
    private var currentTurn: Turn = Turn.X,
    private val gameResultManager: GameResultManager
) {

    private val tictactocMap = TictactocMap()

    init {
        tictactocMap.createBoard(MAP_SIZE)
    }

    var isFinish: Boolean = false
        private set

    private var count: Int = 0

    fun getMap(): Array<Array<Turn>> {
        return tictactocMap.getMap()
    }

    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    private fun isValidData(point: Point): Boolean {
        return !(tictactocMap.getMapRowColumn(row = point.row, column = point.column) != Turn.UNKNOWN || isFinish)
    }

    fun put(point: Point): GameResult {
        val row = point.row
        val column = point.column

        if (!isValidData(point)) {
            return if (isFinish) {
                GameResult.FINISH_GAME
            } else {
                GameResult.INVALID_POSITION
            }
        }

        tictactocMap.setMapRowColumn(row = row, column = column, turn = currentTurn)
        count++
        val gameResult = gameResultManager.getTurnResult(point, tictactocMap.getMap(), currentTurn, count)
        isFinish = gameResult != GameResult.UNKNOWN
        return gameResult
    }

    fun reset() {
        isFinish = false
        count = 0
        currentTurn = Turn.X
        tictactocMap.createBoard(MAP_SIZE)
    }

    fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O
        else Turn.X
    }

    companion object {
        const val MAP_SIZE = 3
    }
}
