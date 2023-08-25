package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.PlayerTictactoc
import com.nextstep.edu.tictactoe.domain.RandomMiddleTictactoc
import com.nextstep.edu.tictactoe.domain.RandomNormalTictactoc
import com.nextstep.edu.tictactoe.domain.RandomStrategy

object RandomStrategyModule {

    fun providePlayerTictactoc(): PlayerTictactoc = PlayerTictactoc()

    fun provideRandomMiddleTictactoc(): RandomMiddleTictactoc {
        return RandomMiddleTictactoc(DefaultRandomStrategy())
    }

    fun provideRandomNormalTictactoc(): RandomNormalTictactoc {
        return RandomNormalTictactoc(DefaultRandomStrategy())
    }
}