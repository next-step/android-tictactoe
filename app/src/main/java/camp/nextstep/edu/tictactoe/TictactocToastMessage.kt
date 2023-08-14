package camp.nextstep.edu.tictactoe

enum class TictactocToastMessage(val resId: Int) {
    WrongClick(resId = R.string.wrong_click),
    GameOver(resId = R.string.game_over),
    XWin(resId = R.string.x_win),
    OWin(resId = R.string.o_win),
    Tie(resId = R.string.tie)
}