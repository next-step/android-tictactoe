package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.TictactocMap
import com.nextstep.edu.tictactoe.domain.model.Turn

interface TictactocStrategy {

    fun put(point: Point): GameResult

    fun isValidData(point: Point): Boolean

    fun getGameResult(point: Point): GameResult

    fun resetMap()

    fun changeTurn()

    fun getCurrentTurn(): Turn

    fun getMap(): Array<Array<Turn>>
}