package com.nextstep.edu.tictactoe.domain

import com.nextstep.edu.tictactoe.domain.model.GameResult
import com.nextstep.edu.tictactoe.domain.model.Point

class PlayerTictactoc: DefaultTictactoe() {

    override fun put(point: Point): GameResult {
        if (!isValidData(point)) {
            return if (isFinish) GameResult.FINISH_GAME else GameResult.INVALID_POSITION
        }

        return getGameResult(point = point)
    }
}