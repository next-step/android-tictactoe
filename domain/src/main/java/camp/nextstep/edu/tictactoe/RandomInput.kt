package camp.nextstep.edu.tictactoe

import kotlin.random.Random

// 랜덤인 경우 빈 배열 찾아서 input
internal object RandomInput {

    fun getRandomPositionList(board: TicTacToeBoard): List<Pair<Int, Int>> {
        var getEmptyBoardPositionList = listOf<Pair<Int, Int>>()

        board.getAllCell().forEachIndexed { xIndex, oxen ->
            oxen.forEachIndexed { yIndex, ox ->
                if (ox == null)
                    getEmptyBoardPositionList = getEmptyBoardPositionList + (xIndex to yIndex)
            }
        }

        return getEmptyBoardPositionList
    }


    fun getRandomPosition(positionList: List<Pair<Int, Int>>): Pair<Int, Int> {
        val random = Random.nextInt(0, positionList.size)
        return positionList[random]
    }
}