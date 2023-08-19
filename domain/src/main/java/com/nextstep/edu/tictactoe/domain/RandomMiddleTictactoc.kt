package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.Behavior
import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.RandomBehavior
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

class RandomMiddleTictactoc : TictactocStrategy {

    override fun put(point: Point, tictactocMap: TictactocMap): GameResult {
        val isValidData = tictactocMap.validData(point = point)
        if (!isValidData) {
            return if (tictactocMap.getIsFinish()) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        var gameResult = tictactocMap.getGameResultFromSetMapPoint(point = point)

        if (gameResult == GameResult.UNKNOWN) {
            val behaviorList = ArrayList<RandomBehavior>().apply {
                add(isLeftDialogBehavior(tictactocMap = tictactocMap))
                add(isRightDialogBehavior(tictactocMap = tictactocMap))
            }

            for (index in 0 until 3) {
                behaviorList.add(isRowBehavior(row = index, tictactocMap = tictactocMap))
                behaviorList.add(isColumnBehavior(column = index, tictactocMap = tictactocMap))
            }

            behaviorList.find {
                it.behavior == Behavior.WIN
            }?.let {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = it.point)
            }

            behaviorList.find {
                it.behavior == Behavior.INTERRUPT
            }?.let {
                tictactocMap.changeTurn()
                return tictactocMap.getGameResultFromSetMapPoint(point = it.point)
            }

            gameResult = randomPut(point = point, tictactocMap = tictactocMap)
        }

        return gameResult
    }

    private fun randomPut(point: Point, tictactocMap: TictactocMap): GameResult {
        tictactocMap.changeTurn()
        val range = (0 until DefaultTictactoe.MAP_SIZE)

        var randomPoint = point

        while (!tictactocMap.validData(point = point)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return tictactocMap.getGameResultFromSetMapPoint(point = randomPoint)
    }

    private fun isLeftDialogBehavior(tictactocMap: TictactocMap): RandomBehavior {
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

    private fun isRightDialogBehavior(tictactocMap: TictactocMap): RandomBehavior {
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

    private fun isColumnBehavior(column: Int, tictactocMap: TictactocMap): RandomBehavior {
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

    private fun isRowBehavior(row: Int, tictactocMap: TictactocMap): RandomBehavior {
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