package camp.nextstep.edu.tictactoe

class TicTacToeGame(
    val board: TicTacToeBoard = TicTacToeBoard(),
) {
    private var gameMode: GameMode = GameMode.TWO_PLAYERS

    // 현재 게임 상태
    private var currentGameStatus: TicTacToeStatus = getGameStatus()


    // 체크 순서
    var gameTurn = OX.initTurn
        private set

    fun changeGameMode(gameMode: GameMode) {
        this.gameMode = gameMode
    }


    fun putCell(position: Position) {
        require(currentGameStatus == TicTacToeStatus.PLAYING) {
            "게임이 종료되었습니다."
        }

        require(board.isExistedPosition(position) && board.isEmptyCell(position)) {
            "잘못된 위치입니다."
        }

        board.put(position, gameTurn)
        gameTurn = gameTurn.change()
        currentGameStatus = getGameStatus()
    }

    fun reset() {
        board.reset()
        currentGameStatus = TicTacToeStatus.PLAYING
        gameTurn = OX.initTurn
    }

    fun getCurrentGameState() = currentGameStatus

    // 배열이 가득 차거나 게임 승부가 나지 않은 상태
    private fun isDraw(isNotExistedWinner: Boolean): Boolean = isNotExistedWinner && board.isFullBoard()

    private fun getGameStatus(): TicTacToeStatus {
        val winner = Winning.getWinner(board)
        return when {
            isDraw(winner == null) -> TicTacToeStatus.DRAW
            winner == OX.X -> TicTacToeStatus.X_WIN
            winner == OX.O -> TicTacToeStatus.O_WIN
            else -> TicTacToeStatus.PLAYING
        }
    }
}