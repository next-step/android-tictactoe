package camp.nextstep.edu.tictactoe.domain.model

import kotlin.random.Random

data class Board private constructor(
    private val board: Map<Position, Cell>
) {
    //바깥에서 map.get()해서 가져오면 불편하니깐 미리 만들어 놓음
    val topLeft: Cell = board.getValue(Position.CellTopLeft) //없으면 exception 발생
    val top: Cell = board.getValue(Position.CellTop)
    val topRight: Cell = board.getValue(Position.CellTopRight)
    val middleLeft: Cell = board.getValue(Position.CellMiddleLeft)
    val middle: Cell = board.getValue(Position.CellMiddle)
    val middleRight: Cell = board.getValue(Position.CellMiddleRight)
    val bottomLeft: Cell = board.getValue(Position.CellBottomLeft)
    val bottom: Cell = board.getValue(Position.CellBottom)
    val bottomRight: Cell = board.getValue(Position.CellBottomRight)

    private val horizontalLine1 = Line.of(topLeft, top, topRight)
    private val horizontalLine2 = Line.of(middleLeft, middle, middleRight)
    private val horizontalLine3 = Line.of(bottomLeft, bottom, bottomRight)
    private val verticalLine1 = Line.of(topLeft, middleLeft, bottomLeft)
    private val verticalLine2 = Line.of(top, middle, bottom)
    private val verticalLine3 = Line.of(topRight, middleRight, bottomRight)
    private val diagonalLine1 = Line.of(topLeft, middle, bottomRight)
    private val diagonalLine2 = Line.of(topRight, middle, bottomLeft)

    val lines = Lines.of(
        horizontalLine1, horizontalLine2, horizontalLine3,
        verticalLine1, verticalLine2, verticalLine3,
        diagonalLine1, diagonalLine2
    )

    private val cells: List<Cell> = listOf(
        topLeft, top, topRight, middleLeft, middle, middleRight, bottomLeft, bottom, bottomRight
    )

    fun isDuplicatedInput(position: Position): Boolean {
        val selectedCell = board.getValue(position)
        return (selectedCell !is Cell.Empty)
    }

    fun mark(cell: Cell): Board {
        if (isDuplicatedInput(cell.position)) return this
        val newBoard = board.toMutableMap()
        newBoard[cell.position] = cell
        return Board(newBoard.toMap()) //방어적 복사
    }

    fun getRandomCell(): Cell {
        val emptyCells = cells.filterIsInstance<Cell.Empty>()
        val randomIndex = Random.nextInt(0, emptyCells.size)
        return emptyCells[randomIndex]
    }

    companion object {
        val EMPTY = Board(
            board = setOf(
                Cell.Empty(Position.CellTopLeft),
                Cell.Empty(Position.CellTop),
                Cell.Empty(Position.CellTopRight),
                Cell.Empty(Position.CellMiddleLeft),
                Cell.Empty(Position.CellMiddle),
                Cell.Empty(Position.CellMiddleRight),
                Cell.Empty(Position.CellBottomLeft),
                Cell.Empty(Position.CellBottom),
                Cell.Empty(Position.CellBottomRight),
            ).associateBy { it.position }
        )
    }
}
