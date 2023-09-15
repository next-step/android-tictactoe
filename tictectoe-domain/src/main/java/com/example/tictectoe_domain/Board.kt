package com.example.tictectoe_domain

data class Board private constructor(private val board: Map<Position, Cell>) {
    val topLeft: Cell = board.getValue(Position.TOP_LEFT)
    val topCenter: Cell = board.getValue(Position.TOP_CENTER)
    val topRight: Cell = board.getValue(Position.TOP_RIGHT)
    val midLeft: Cell = board.getValue(Position.MID_LEFT)
    val midCenter: Cell = board.getValue(Position.MID_CENTER)
    val midRight: Cell = board.getValue(Position.MID_RIGHT)
    val bottomLeft: Cell = board.getValue(Position.BOTTOM_LEFT)
    val bottomCenter: Cell = board.getValue(Position.BOTTOM_CENTER)
    val bottomRight: Cell = board.getValue(Position.BOTTOM_RIGHT)

    private val topHorizontalLine = mutableListOf(topLeft, topCenter, topRight)
    private val midHorizontalLine = mutableListOf(midLeft, midCenter, midRight)
    private val bottomHorizontalLine = mutableListOf(bottomLeft, bottomCenter, bottomRight)

    private val leftVerticalLine = mutableListOf(topLeft, midLeft, bottomLeft)
    private val centerVerticalLine = mutableListOf(topCenter, midCenter, bottomCenter)
    private val rightVerticalLine = mutableListOf(topRight, midRight, bottomRight)

    private val rightDiagonalLine = mutableListOf(topLeft, midCenter, bottomRight)
    private val leftDiagonalLine = mutableListOf(topRight, midCenter, bottomLeft)

    private val _lines = mutableListOf(
        topHorizontalLine, midHorizontalLine, bottomHorizontalLine,
        leftVerticalLine, centerVerticalLine, rightVerticalLine,
        rightDiagonalLine, leftDiagonalLine
    )
    val lines: List<List<Cell>>
        get() = _lines

    fun getBoard() = board

    fun mark(cell: Cell): Board {
        val newBoard = board.toMutableMap()
        newBoard[cell.position] = cell
        return Board(newBoard)
    }

    fun canMark(position: Position): Boolean {
        return board[position] == Cell.NONE(position)
    }

    fun isFull(): Boolean {
        var result: Boolean = true
        board.forEach {
            if (it.value == Cell.NONE(it.key)) {
                result = false
            }
        }
        return result
    }

    fun getNonSelectPositionList(): List<Position> {

        val nonCellPositionList = mutableListOf<Position>()
        board.forEach {
            if (it.value == Cell.NONE(it.key)) {
                nonCellPositionList.add(it.key)
            }
        }
        return nonCellPositionList
    }

    companion object {
        val EMPTY: Board = Board(
            setOf(
                Cell.NONE(Position.TOP_LEFT), Cell.NONE(Position.TOP_CENTER), Cell.NONE(Position.TOP_RIGHT),
                Cell.NONE(Position.MID_LEFT), Cell.NONE(Position.MID_CENTER), Cell.NONE(Position.MID_RIGHT),
                Cell.NONE(Position.BOTTOM_LEFT), Cell.NONE(Position.BOTTOM_CENTER), Cell.NONE(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )

        val TEST_PLAYER1_WIN_BOARD = Board(
            setOf(
                Cell.PLAYER1(Position.TOP_LEFT), Cell.PLAYER1(Position.TOP_CENTER), Cell.PLAYER1(Position.TOP_RIGHT),
                Cell.NONE(Position.MID_LEFT), Cell.NONE(Position.MID_CENTER), Cell.NONE(Position.MID_RIGHT),
                Cell.NONE(Position.BOTTOM_LEFT), Cell.NONE(Position.BOTTOM_CENTER), Cell.NONE(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )

        val TEST_BEFORE_PLAYER1_WIN_BOARD = Board(
            setOf(
                Cell.NONE(Position.TOP_LEFT), Cell.PLAYER1(Position.TOP_CENTER), Cell.PLAYER1(Position.TOP_RIGHT),
                Cell.NONE(Position.MID_LEFT), Cell.NONE(Position.MID_CENTER), Cell.NONE(Position.MID_RIGHT),
                Cell.NONE(Position.BOTTOM_LEFT), Cell.NONE(Position.BOTTOM_CENTER), Cell.NONE(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )

        val TEST_DRAW_BOARD = Board(
            setOf(
                Cell.PLAYER1(Position.TOP_LEFT), Cell.PLAYER1(Position.TOP_CENTER), Cell.PLAYER2(Position.TOP_RIGHT),
                Cell.PLAYER2(Position.MID_LEFT), Cell.PLAYER1(Position.MID_CENTER), Cell.PLAYER1(Position.MID_RIGHT),
                Cell.PLAYER1(Position.BOTTOM_LEFT), Cell.PLAYER2(Position.BOTTOM_CENTER), Cell.PLAYER2(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )

        val TEST_BEFORE_DRAW_BOARD = Board(
            setOf(
                Cell.NONE(Position.TOP_LEFT), Cell.PLAYER1(Position.TOP_CENTER), Cell.PLAYER2(Position.TOP_RIGHT),
                Cell.PLAYER2(Position.MID_LEFT), Cell.PLAYER1(Position.MID_CENTER), Cell.PLAYER1(Position.MID_RIGHT),
                Cell.PLAYER1(Position.BOTTOM_LEFT), Cell.PLAYER2(Position.BOTTOM_CENTER), Cell.PLAYER2(Position.BOTTOM_RIGHT)
            ).associateBy { it.position }
        )
    }
}
