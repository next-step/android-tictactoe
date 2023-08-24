package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.RandomMiddleTictactoc
import com.nextstep.edu.tictactoe.domain.RandomNormalTictactoc
import com.nextstep.edu.tictactoe.domain.RandomStrategy

object RandomStrategyModule {

    fun provideRandomMiddleTictactoc(): RandomStrategy {
        return RandomMiddleTictactoc(DefaultRandomStrategy())
    }

    fun provideRandomNormalTictactoc(): RandomStrategy {
        return RandomNormalTictactoc(DefaultRandomStrategy())
    }
}