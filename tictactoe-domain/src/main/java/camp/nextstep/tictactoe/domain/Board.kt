package camp.nextstep.tictactoe.domain

data class Board(
	private val size: Int = DEFAULT_SIZE,
	val map: Map<Point, Marker> = mapOf(),
) {

	operator fun set(point: Point, marker: Marker): Board {
		return if (map.contains(point)) {
			this
		} else {
			this.copy(map = map + mapOf(point to marker))
		}
	}

	fun clear(): Board {
		return this.copy(map = mapOf())
	}

	companion object {
		const val DEFAULT_SIZE = 3

		val EMPTY = Board()
	}
}