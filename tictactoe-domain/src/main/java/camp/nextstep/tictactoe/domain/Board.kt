package camp.nextstep.tictactoe.domain

data class Board(
	val size: Int = DEFAULT_SIZE,
	private val map: Map<Point, Marker> = mapOf(),
) {
	val totalMarkerCount = map.size

	operator fun get(x: Int, y: Int): Marker? {
		return map[Point(x, y)]
	}

	operator fun set(point: Point, marker: Marker): Board? {
		return if (map.contains(point)) {
			null
		} else {
			this.copy(map = map + mapOf(point to marker))
		}
	}

	fun filter(predicate: (Map.Entry<Point, Marker>) -> Boolean): Map<Point, Marker> {
		return this.map.filter(predicate)
	}

	fun clear(): Board {
		return this.copy(map = mapOf())
	}

	companion object {
		const val DEFAULT_SIZE = 3

		val EMPTY = Board()
	}
}