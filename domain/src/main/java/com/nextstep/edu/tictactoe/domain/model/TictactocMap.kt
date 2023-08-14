package com.nextstep.edu.tictactoe.domain.model

class TictactocMap {

    private lateinit var map: Array<Array<Turn>>

    fun createBoard(size: Int) {
        map = Array(size) { Array(size) { Turn.UNKNOWN } }
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
}