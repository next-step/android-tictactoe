package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.RandomBehavior
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

class RandomMiddleTictactoc : TictactocStrategy {

    private val tictactocMap: TictactocMap = TictactocMap()

    override fun put(point: Point): GameResult {
        if (!isValidData(point)) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = getGameResult(point = point)

        if (gameResult == GameResult.UNKNOWN) {
            val behaviorList = ArrayList<RandomBehavior>().apply {
                add(isLeftDialogBehavior())
                add(isRightDialogBehavior())
            }

            for (index in 0 until 3) {
                behaviorList.add(isRowBehavior(index))
                behaviorList.add(isColumnBehavior(index))
            }

            behaviorList.find {
                it.behavior == Behavior.WIN
            }?.let {
                tictactocMap.changeTurn()
                return getGameResult(it.point)
            }

            behaviorList.find {
                it.behavior == Behavior.INTERRUPT
            }?.let {
                tictactocMap.changeTurn()
                return getGameResult(it.point)
            }

            gameResult = randomPut(point = point)
        }

        return gameResult
    }

    override fun isValidData(point: Point): Boolean {
        return tictactocMap.validData(point = point)
    }

    override fun getGameResult(point: Point): GameResult {
        return tictactocMap.getGameResultFromSetMapPoint(point = point)
    }

    override fun resetMap() = tictactocMap.resetMap()

    override fun changeTurn() = tictactocMap.changeTurn()

    override fun getCurrentTurn(): Turn = tictactocMap.getCurrentTurn()

    override fun getMap(): Array<Array<Turn>> = tictactocMap.getMap()

    private fun randomPut(point: Point): GameResult {
        tictactocMap.changeTurn()
        val range = (0 until DefaultTictactoe.MAP_SIZE)

        var randomPoint = point
        while (!isValidData(randomPoint)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint)
    }

    private fun isLeftDialogBehavior(): RandomBehavior {
        val map = tictactocMap.getMap()
        var dialogSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = point)
                Turn.O -> dialogSum += 1
                Turn.X -> dialogSum -= 1
            }
        }

        return when (dialogSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isRightDialogBehavior(): RandomBehavior {
        val map = tictactocMap.getMap()
        var dialogSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][2-point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = point)
                Turn.O -> dialogSum += 1
                Turn.X -> dialogSum -= 1
            }
        }

        return when (dialogSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isColumnBehavior(column: Int): RandomBehavior {
        val map = tictactocMap.getMap()
        var columnSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[point][column]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = point, column = column)
                Turn.O -> columnSum += 1
                Turn.X -> columnSum -= 1
            }
        }

        return when (columnSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }

    private fun isRowBehavior(row: Int): RandomBehavior {
        val map = tictactocMap.getMap()
        var columnSum = 0
        var putPoint = Point.of(0, 0)
        for (point in 0 until 3) {
            val turn = map[row][point]
            when (turn) {
                Turn.UNKNOWN -> putPoint = Point.of(row = row, column = point)
                Turn.O -> columnSum += 1
                Turn.X -> columnSum -= 1
            }
        }

        return when (columnSum) {
            2 -> RandomBehavior(behavior = Behavior.WIN, putPoint)
            -2 -> RandomBehavior(behavior = Behavior.INTERRUPT, putPoint)
            else -> RandomBehavior(behavior = Behavior.UNKNOWN, putPoint)
        }
    }
}