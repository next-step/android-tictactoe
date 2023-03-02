package camp.nextstep.edu.tictactoe.domain.model

sealed class State {
    object InProgress : State()

    sealed class Ended : State()
    object Draw : Ended()
    object WinX : Ended()
    object WinO : Ended()
}