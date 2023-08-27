package camp.nextstep.tictactoe.domain

sealed interface SetBoardStatus {
	object AlreadyExist : SetBoardStatus
	data class Success(val board: Board) : SetBoardStatus
}

data class Result(
	val xCount: Int,
	val oCount: Int,
	val remainPointsInLine: List<Point>,
)

data class Board(
	private val size: Int = DEFAULT_SIZE,
	private val map: Map<Point, Marker> = mapOf(),
) {
	private fun getLines(): List<Line> {
		val rowLines = mutableListOf<Line>()
		val colLines = mutableListOf<Line>()
		val ltrDiagonalPoints = mutableListOf<Point>()
		val rtlDiagonalPoints = mutableListOf<Point>()

		for (i in 0 until size) {
			val rowPoints = mutableListOf<Point>()
			val colPoints = mutableListOf<Point>()
			for (j in 0 until size) {
				rowPoints.add(Point(i, j))
				colPoints.add(Point(j, i))
			}
			rowLines.add(Line(rowPoints))
			colLines.add(Line(colPoints))
			ltrDiagonalPoints.add(Point(i, i))
			rtlDiagonalPoints.add(Point(i, size - 1 - i))
		}

		return rowLines + colLines + Line(ltrDiagonalPoints) + Line(rtlDiagonalPoints)
	}

	operator fun get(x: Int, y: Int): Marker? {
		return map[Point(x, y)]
	}

	operator fun set(point: Point, marker: Marker): SetBoardStatus {
		return if (map.contains(point)) {
			SetBoardStatus.AlreadyExist
		} else {
			SetBoardStatus.Success(this.copy(map = map + mapOf(point to marker)))
		}
	}

	fun getWinner(): Marker? {
		val lines = getLines()

		lines.forEach { line ->
			val result = getResult(line)

			if (result.xCount == size) {
				return Marker.X
			} else if (result.oCount == size) {
				return Marker.O
			}
		}

		return null
	}

	private fun getResult(line: Line): Result {
		var xCount = 0
		var oCount = 0
		val remainPointsInLine = mutableListOf<Point>()

		line.points.forEach { point ->
			when (map[point]) {
				Marker.X -> xCount++
				Marker.O -> oCount++
				null -> remainPointsInLine.add(point)
			}
		}

		return Result(xCount, oCount, remainPointsInLine)
	}

	fun getRemainPoints(): List<Point> {
		val allPoints = getAllPoints()
		val occupiedPoints = getOccupiedPoints()

		return allPoints.minus(occupiedPoints)
	}

	private fun getAllPoints(): List<Point> {
		val points = mutableListOf<Point>()

		repeat(size) { x ->
			repeat(size) { y ->
				points.add(Point(x, y))
			}
		}

		return points
	}

	private fun getOccupiedPoints(): List<Point> {
		return map.map { it.key }.toList()
	}

	fun getOneRemainPoints(marker: Marker): List<Point> {
		val lines = getLines()
		var oneRemainPoints = listOf<Point>()

		lines.forEach { line ->
			val result = getResult(line)
			when (marker) {
				Marker.X -> {
					if (result.xCount == size - 1 && result.oCount == 0) {
						oneRemainPoints = oneRemainPoints.plus(result.remainPointsInLine)
					}
				}

				Marker.O -> {
					if (result.oCount == size - 1 && result.xCount == 0) {
						oneRemainPoints = oneRemainPoints.plus(result.remainPointsInLine)
					}
				}
			}
		}

		return oneRemainPoints
	}

	companion object {
		const val DEFAULT_SIZE = 3

		val EMPTY = Board()
	}
}