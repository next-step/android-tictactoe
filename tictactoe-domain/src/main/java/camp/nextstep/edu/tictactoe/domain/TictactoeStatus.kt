package camp.nextstep.edu.tictactoe.domain

sealed interface TictactoeStatus {
    data object Progress : TictactoeStatus
    data object XWin : TictactoeStatus
    data object OWin : TictactoeStatus
    data object Draw : TictactoeStatus
}
