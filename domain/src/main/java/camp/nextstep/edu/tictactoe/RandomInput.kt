package camp.nextstep.edu.tictactoe

import kotlin.random.Random

// 랜덤인 경우 빈 배열 찾아서 input
object RandomInput {

    fun randomPut(board: TicTacToeBoard) {
        var getEmptyBoardPositionList = listOf<Pair<Int, Int>>()

        board.getAllCell().forEachIndexed { xIndex, oxen ->
            oxen.forEachIndexed { yIndex, ox ->
                if (ox == null)
                    getEmptyBoardPositionList = getEmptyBoardPositionList + (xIndex to yIndex)
            }
        }

        if (getEmptyBoardPositionList.isEmpty()) return
        val position = getRandomPosition(getEmptyBoardPositionList)
        board.twoPlayerPut(position.first, position.second)
    }


    private fun getRandomPosition(positionList: List<Pair<Int, Int>>): Pair<Int, Int> {
        val random = Random.nextInt(0, positionList.size - 1)
        return positionList[random]
    }
}