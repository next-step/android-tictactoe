package camp.nextstep.edu.tictactoe.domain.model

data class TurnResult(val state: State, var cells: Map<String, Cell>) {
    companion object {
        const val KEY_USER = "USER"
        const val KEY_AI = "AI"
    }
}
