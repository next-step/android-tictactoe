package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Status
import com.nextstep.edu.tictactoe.domain.model.Turn

class Tictactoe constructor(
    private var currentTurn: Turn = Turn.X,
    private val gameResultManager: GameResultManager
) {

    private var map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }

    var isFinish: Boolean = false
        private set

    private var count: Int = 0

    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    fun isValidData(point: Point): Boolean {
        return !(map[point.row][point.column] != Turn.UNKNOWN || isFinish)
    }

    fun put(point: Point): Pair<GameResult, Status> {
        val r = point.row
        val c = point.column
        map[r][c] = currentTurn
        count++
        val result = gameResultManager.getTurnResult(point, map, currentTurn, count)
        isFinish = result.first != GameResult.UNKNOWN
        return result
    }

    fun reset() {
        isFinish = false
        count = 0
        currentTurn = Turn.X
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
    }

    fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O
        else Turn.X
    }

    companion object {
        const val MAP_SIZE = 3
    }
}
