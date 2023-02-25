package camp.nextstep.edu.tictactoe.domain

enum class Turn {
    X,
    O,
    UNKNOWN,
}

data class Point(val r: Int, val c: Int) {
    fun getPoint(r: Int, c: Int): Point {
        return Point(r, c)
    }

}

enum class GameResult {
    X_WIN,
    O_WIN,
    TIE,
    UNKNOWN
}

class Ticktacktoe(
    private var currentTurn: Turn = Turn.X
) {
    private var map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }

    var isFinish: Boolean = false
        private set
    private var count: Int = 0


    fun getCurrentTurn(): Turn {
        return currentTurn
    }

    fun isWin(point: Point): GameResult? {
        if (!drawMap(point) || isFinish)
            return null
        val r = point.r
        val c = point.c
        var rSum = 0
        var cSum = 0
        for (k in 0 until MAP_SIZE) {
            rSum += if (map[r][k] == currentTurn) 1 else 0
            cSum += if (map[k][c] == currentTurn) 1 else 0
        }
        var leftDiagonalSum = 0
        var rightDiagonalSum = 0
        for (k in 0 until MAP_SIZE) {
            leftDiagonalSum += if (map[k][k] == currentTurn) 1 else 0
            rightDiagonalSum += if (map[k][MAP_SIZE - k - 1] == currentTurn) 1 else 0
        }

        isFinish = listOf<Int>(rSum, cSum, leftDiagonalSum, rightDiagonalSum).contains(MAP_SIZE)
        val result =
            if (isFinish && currentTurn == Turn.X) GameResult.X_WIN
            else if (isFinish && currentTurn == Turn.O) GameResult.O_WIN
            else if (!isFinish && (count == MAP_SIZE * MAP_SIZE)) {
                isFinish = true
                GameResult.TIE
            } else GameResult.UNKNOWN

        return result
    }

    fun reset() {
        isFinish = false
        count = 0
        map = Array(MAP_SIZE) { Array(MAP_SIZE) { Turn.UNKNOWN } }
    }

    fun changeTurn() {
        currentTurn = if (currentTurn == Turn.X) Turn.O
        else Turn.X
    }

    private fun drawMap(point: Point): Boolean {
        val r = point.r
        val c = point.c
        if (map[r][c] != Turn.UNKNOWN)
            return false
        map[r][c] = currentTurn
        count++
        return true
    }


    companion object {
        const val MAP_SIZE = 3
    }

}

