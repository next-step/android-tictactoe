package camp.nextstep.edu.tictactoe.domain

class TictactoeGame {
    var tictactoeMap = TictactoeMap()
        private set
    var isXTurn: Boolean = true
        private set

    fun setPosition(position: CellPosition): TictactoeStatus {
        tictactoeMap.set(position, isXTurn)
        isXTurn = !isXTurn
        return checkGameResult()
    }

    private fun checkGameResult(): TictactoeStatus {
        return WinnerChecker.check(tictactoeMap)
    }

    fun gameReset() {
        tictactoeMap = TictactoeMap()
        isXTurn = true
    }
}
