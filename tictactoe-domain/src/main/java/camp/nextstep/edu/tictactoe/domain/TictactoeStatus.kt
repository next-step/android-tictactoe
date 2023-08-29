package camp.nextstep.edu.tictactoe.domain

sealed interface TictactoeStatus {
    object Progress : TictactoeStatus
    object XWin : TictactoeStatus
    object OWin : TictactoeStatus
    object Draw : TictactoeStatus
}
