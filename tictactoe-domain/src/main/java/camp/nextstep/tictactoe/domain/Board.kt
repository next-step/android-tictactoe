package camp.nextstep.tictactoe.domain

sealed interface SetBoardStatus {
	object AlreadyExist : SetBoardStatus
	data class Success(val board: Board) : SetBoardStatus
}

data class Board(
	val size: Int = DEFAULT_SIZE,
	private val map: Map<Point, Marker> = mapOf(),
) {
	val totalMarkerCount = map.size

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

	fun filter(predicate: (Map.Entry<Point, Marker>) -> Boolean): Map<Point, Marker> {
		return this.map.filter(predicate)
	}

	fun getKeys(): List<Point> {
		return map.map { it.key }.toList()
	}

	companion object {
		const val DEFAULT_SIZE = 3

		val EMPTY = Board()
	}
}