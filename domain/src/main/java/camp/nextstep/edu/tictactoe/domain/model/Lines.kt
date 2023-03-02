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


    companion object {
        fun of(vararg lines: Line): Lines {
            return Lines(lines.toSet())
        }
    }

}