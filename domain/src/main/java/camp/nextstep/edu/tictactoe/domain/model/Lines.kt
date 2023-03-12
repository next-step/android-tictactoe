package camp.nextstep.edu.tictactoe.domain.model

@Suppress("DataClassPrivateConstructor")
data class Lines private constructor(
    private val lines: Set<Line>,
) {
    fun isX(): Boolean =
        lines.any { line -> line.isX() }


    fun isO(): Boolean =
        lines.any { line -> line.isO() }

    fun isDraw(): Boolean =
        lines.all { line -> line.isDraw() }

    fun findXWinningPosition(): Position? {
        val xWinningPositions = lines.mapNotNull { line -> line.findXWinningPosition() }
        return xWinningPositions.firstOrNull()
    }

    fun findOWinningPosition(): Position? {
        val oWinningPositions = lines.mapNotNull { line -> line.findOWinningPosition() }
        return oWinningPositions.firstOrNull()
    }

    companion object {
        fun of(vararg lines: Line): Lines {
            return Lines(lines.toSet())
        }
    }

}
