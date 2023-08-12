package camp.nextstep.edu.tictactoe

sealed class TictactocToastMessage {
    object WrongClick: TictactocToastMessage()
    object GameOver: TictactocToastMessage()
    object XWin: TictactocToastMessage()
    object OWin: TictactocToastMessage()
    object Tie: TictactocToastMessage()
}