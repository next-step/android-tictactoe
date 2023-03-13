package camp.nextstep.edu.tictactoe.domain.model

sealed class TurnError(message: String) : Exception(message) {
    class GameFinish(message: String) : TurnError(message)
    class DuplicatedInput(message: String) : TurnError(message)
}

