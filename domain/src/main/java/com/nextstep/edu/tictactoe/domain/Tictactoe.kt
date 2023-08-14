package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Status
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

    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    private fun isValidData(point: Point): Boolean {
        return !(tictactocMap.getMapRowColumn(row = point.row, column = point.column) != Turn.UNKNOWN || isFinish)
    }

    fun put(point: Point): Pair<GameResult, Status> {
        val row = point.row
        val column = point.column

        if (!isValidData(point)) {
            return if (isFinish) {
                Pair(GameResult.FINISH_GAME, Status(point = point, turn = currentTurn))
            } else {
                Pair(GameResult.INVALID_POSITION, Status(point = point, turn = currentTurn))
            }
        }

        tictactocMap.setMapRowColumn(row = row, column = column, turn = currentTurn)
        count++
        val result = gameResultManager.getTurnResult(point, tictactocMap.getMap(), currentTurn, count)
        isFinish = result.first != GameResult.UNKNOWN
        return result
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
