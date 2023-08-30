package camp.nextstep.edu.tictactoe.domain

class TictactoeGame {
    var tictactoeMap = TictactoeMap()
        private set
    var isXTurn: Boolean = true
        private set

    fun setPosition(position: CellPosition): TictactoeStatus {
        tictactoeMap.set(position, isXTurn)
        isXTurn = !isXTurn
        return WinnerChecker.check(tictactoeMap.positions)
    }

    fun gameReset() {
        tictactoeMap = TictactoeMap()
        isXTurn = true
    }
}
