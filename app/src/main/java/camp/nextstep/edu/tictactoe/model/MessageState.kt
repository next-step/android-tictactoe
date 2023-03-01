package camp.nextstep.edu.tictactoe.model


sealed class TurnResultMessage(open val message: String) {

    sealed class ErrorMessage(override val message: String) : TurnResultMessage(message) {
        object WrongClick : ErrorMessage("다른 곳을 선택해 주세요")
        object FinishGame : ErrorMessage("게임이 종료되었습니다")
    }

    sealed class GameResultMessage(override val message: String) : TurnResultMessage(message) {
        object XWin : GameResultMessage("x 가 이겼습니다")
        object OWin : GameResultMessage("o 가 이겼습니다")
        object Tie : GameResultMessage("무승부 입니다")

    }


}
