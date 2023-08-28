package com.nextstep.edu.tictactoe.domain.usecase

import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.TictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoe
import com.nextstep.edu.tictactoe.domain.model.GameMode
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import javax.inject.Inject

class GetTictactoeModeUseCase @Inject constructor(
    private val tictactoeMap: TictactoeMap,
    private val playerTictactoe: PlayerTictactoe,
    private val randomNormalTictactoe: RandomNormalTictactoe,
    private val randomMiddleTictactoe: RandomMiddleTictactoe
) {
    operator fun invoke(gameMode: GameMode): Tictactoe {
        return when (gameMode) {
            GameMode.TWO_PLAYER -> TictactoeImpl(strategy = playerTictactoe, tictactoeMap = tictactoeMap)
            GameMode.RANDOM -> TictactoeImpl(strategy = randomNormalTictactoe, tictactoeMap = tictactoeMap)
            GameMode.RANDOM_MIDDLE -> TictactoeImpl(strategy = randomMiddleTictactoe, tictactoeMap = tictactoeMap)
        }
    }
}