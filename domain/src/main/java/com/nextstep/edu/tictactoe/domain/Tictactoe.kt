package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point
import com.nextstep.edu.tictactoe.domain.model.Turn

interface Tictactoe {

    fun put(point: Point): GameResult

    fun reset()

    fun changeTurn()

    fun getCurrentTurn(): Turn

    fun getMap(): Array<Array<Turn>>

    companion object {
        const val MAP_SIZE: Int = 3
    }
}