package com.nextstep.edu.tictactoe.domain.model

import com.nextstep.edu.tictactoe.domain.Tictactoe.Companion.MAP_SIZE

class TictactocMap {

    private var map: Array<Array<Turn>> = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }

    fun resetMap() {
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
    }

    fun setMapRowColumn(row: Int, column: Int, turn: Turn) {
        map[row][column] = turn
    }

    fun getMapRowColumn(row: Int, column: Int): Turn {
        return map[row][column]
    }

    fun getMap(): Array<Array<Turn>> {
        return map
    }

    fun validData(point: Point, isFinish: Boolean): Boolean {
        return !(map[point.row][point.column] != Turn.UNKNOWN || isFinish)
    }
}