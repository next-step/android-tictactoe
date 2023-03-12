package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.*

class DrawAiTicktacktoe : Ticktacktoe(Turn.X) {
    private fun putDrawPosition(): Position {
        return listOf(
            findCenterPosition(),findOWin(),findXWin(),findPlayerForkPosition(),
            findOppositeCornerPosition(),findCornerPosition()
        ).first{ it!= null} ?: run{
            findEmptyPosition()
        }
    }

    override fun runOneTurn(position: Position): TurnResult {
        val userGameResult = super.put(position)
        switchTurn()

        if (userGameResult.first == State.InProgress) {
            val aiPoint = putDrawPosition()
            val aiGameResult = super.put(aiPoint)
            switchTurn()
            return TurnResult(
                aiGameResult.first, aiGameResult.second
            )

        }
        return TurnResult(
            userGameResult.first,
            userGameResult.second
        )
    }

    // 중앙이 비어있으면 중앙에 넣는다.
    private fun findCenterPosition(): Position? {
        return board.findCenterPosition()
    }
    // 내가 이기는 수가 있으면 이기는 수를 둔다.
    private fun findXWin(): Position? {
        return board.lines.findXWinningPosition()
    }

    private fun findOWin(): Position? {
        return board.lines.findOWinningPosition()
    }
    // 사이드를 막는다
    private fun findPlayerForkPosition(): Position? {
        return board.findPlayerForkPosition()
    }
    // 상대가 가진 코너가 1개일 경우 반대편 에 둔다
    private fun findOppositeCornerPosition(): Position? {
        return board.findOppositeCornerPosition()
    }
    // 코너에 둔다.
    private fun findCornerPosition(): Position? {
        return board.findCornerPosition()
    }
    // 남는 곳에 둔다
    private fun findEmptyPosition(): Position {
        return board.findEmptyPosition()
    }
}
