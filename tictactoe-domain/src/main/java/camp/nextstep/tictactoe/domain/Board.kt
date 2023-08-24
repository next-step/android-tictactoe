package camp.nextstep.tictactoe.domain

sealed interface SetBoardStatus {
	object AlreadyExist : SetBoardStatus
	data class Success(val board: Board) : SetBoardStatus
}

data class Board(
	private val size: Int = DEFAULT_SIZE,
	private val map: Map<Point, Marker> = mapOf(),
) {
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
		// 행
		for (row in 0 until size) {
			getWinner { point -> point.x == row }?.let { return it }
		}

		// 열
		for (col in 0 until size) {
			getWinner { point -> point.y == col }?.let { return it }
		}

		// 왼쪽 -> 오른쪽 대각선
		getWinner { point -> point.x == point.y }?.let { return it }

		// 오른쪽 -> 왼쪽 대각선
		getWinner { point -> point.x + point.y == size - 1 }?.let { return it }

		return null
	}

	private fun getWinner(condition: (Point) -> Boolean): Marker? {
		return when {
			hasWinner { (point, marker) -> condition(point) && marker == Marker.X } -> Marker.X
			hasWinner { (point, marker) -> condition(point) && marker == Marker.O } -> Marker.O
			else -> null
		}
	}

	private fun hasWinner(predicate: (Map.Entry<Point, Marker>) -> Boolean): Boolean {
		val count = map.filter(predicate).size
		return count == size
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

	companion object {
		const val DEFAULT_SIZE = 3

		val EMPTY = Board()
	}
}