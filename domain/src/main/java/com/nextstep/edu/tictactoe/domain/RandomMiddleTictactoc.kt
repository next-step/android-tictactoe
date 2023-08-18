package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Turn

class RandomMiddleTictactoc : DefaultTictactoe() {

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
                changeTurn()
                return getGameResult(it.point)
            }

            behaviorList.find {
                it.behavior == Behavior.INTERRUPT
            }?.let {
                changeTurn()
                return getGameResult(it.point)
            }

            gameResult = randomPut(point = point)
        }

        return gameResult
    }

    private fun randomPut(point: Point): GameResult {
        changeTurn()
        val range = (0 until MAP_SIZE)

        var randomPoint = point
        while (!isValidData(randomPoint)) {
            val row = range.random()
            val column = range.random()
            randomPoint = Point.of(row = row, column = column)
        }

        return getGameResult(point = randomPoint)
    }

    private fun isLeftDialogBehavior(): RandomBehavior {
        val map = getMap()
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
        val map = getMap()
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
        val map = getMap()
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
        val map = getMap()
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

data class RandomBehavior(
    val behavior: Behavior,
    val point: Point
)

enum class Behavior {
    INTERRUPT, WIN, UNKNOWN
}