package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.RandomStrategy
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoe
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoeImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TictactocModule {

    @Binds
    @Singleton
    internal abstract fun providePlayerTictactoc(
        playerTictactoeImpl: PlayerTictactoeImpl
    ): PlayerTictactoe

    @Binds
    @Singleton
    internal abstract fun provideRandomNormalTictactoc(
        randomNormalTictactoeImpl: RandomNormalTictactoeImpl
    ): RandomNormalTictactoe

    @Binds
    @Singleton
    internal abstract fun provideRandomMiddleTictactoc(
        randomMiddleTictactoeImpl: RandomMiddleTictactoeImpl
    ): RandomMiddleTictactoe
}