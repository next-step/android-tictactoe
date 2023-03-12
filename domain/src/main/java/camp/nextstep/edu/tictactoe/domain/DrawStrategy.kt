package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.model.Board
import camp.nextstep.edu.tictactoe.domain.model.Position

internal class DrawStrategy : AiStrategy {
    override fun getAiPosition(board: Board): Position {
        return listOf(
            findCenterPosition(board),
            findOWin(board),
            findXWin(board),
            findPlayerForkPosition(board),
            findOppositeCornerPosition(board),
            findCornerPosition(board)
        ).first { it != null } ?: run {
            findEmptyPosition(board)
        }
    }

    // 중앙이 비어있으면 중앙에 넣는다.
    private fun findCenterPosition(board: Board): Position? {
        return board.findCenterPosition()
    }

    // 내가 이기는 수가 있으면 이기는 수를 둔다.
    private fun findXWin(board: Board): Position? {
        return board.lines.findXWinningPosition()
    }

    private fun findOWin(board: Board): Position? {
        return board.lines.findOWinningPosition()
    }

    // 사이드를 막는다
    private fun findPlayerForkPosition(board: Board): Position? {
        return board.findPlayerForkPosition()
    }

    // 상대가 가진 코너가 1개일 경우 반대편 에 둔다
    private fun findOppositeCornerPosition(board: Board): Position? {
        return board.findOppositeCornerPosition()
    }

    // 코너에 둔다.
    private fun findCornerPosition(board: Board): Position? {
        return board.findCornerPosition()
    }

    // 남는 곳에 둔다
    private fun findEmptyPosition(board: Board): Position {
        return board.findEmptyPosition()
    }

}
