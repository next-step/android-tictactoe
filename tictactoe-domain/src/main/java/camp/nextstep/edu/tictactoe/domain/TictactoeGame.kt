package camp.nextstep.edu.tictactoe.domain

class TictactoeGame {
    var tictactoeMap = TictactoeMap()
    var isXTurn: Boolean = true

    fun setPosition(position: CellPosition): GameResult<Int> {
        tictactoeMap.set(position, isXTurn)
        isXTurn = !isXTurn
        return checkGameResult()
    }

    private fun checkGameResult(): GameResult<Int> {
        if (tictactoeMap.isAllOccupied()) {
            return GameResult.GameStatus(GameResult.GAME_DRAW)
        }
        return tictactoeMap.checkWin()
    }

    fun gameReset() {
        tictactoeMap = TictactoeMap()
        isXTurn = true
    }
}
