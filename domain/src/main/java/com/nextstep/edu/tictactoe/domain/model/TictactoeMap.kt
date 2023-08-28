package com.nextstep.edu.tictactoe.domain.model

interface TictactoeMap {

    fun resetMap()

    fun changeTurn()

    fun getMapRowColumn(row: Int, column: Int): Turn

    fun getMap(): Array<Array<Turn>>

    fun validData(point: Point): Boolean

    fun getIsFinish(): Boolean

    fun getCurrentTurn(): Turn

    fun getGameResultFromSetMapPoint(point: Point, ): GameResult

    fun getNextPutPointsFromBehavior(behavior: Behavior): List<Point>
}