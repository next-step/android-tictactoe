package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.RandomStrategy
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoe
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoeImpl
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TictactocModule {

    private fun provideDefaultRandomStrategy(): RandomStrategy = DefaultRandomStrategy()

    @Provides
    @Singleton
    fun providePlayerTictactoc(
        tictactoeMap: TictactoeMap
    ): PlayerTictactoe = PlayerTictactoeImpl(tictactoeMap = tictactoeMap)

    @Provides
    @Singleton
    fun provideRandomNormalTictactoc(
        tictactoeMap: TictactoeMap
    ): RandomNormalTictactoe =
        RandomNormalTictactoeImpl(
            randomStrategy = provideDefaultRandomStrategy(),
            tictactoeMap = tictactoeMap
        )

    @Provides
    @Singleton
    fun provideRandomMiddleTictactoc(
        tictactoeMap: TictactoeMap
    ): RandomMiddleTictactoe =
        RandomMiddleTictactoeImpl(
            randomStrategy = provideDefaultRandomStrategy(),
            tictactoeMap = tictactoeMap
        )
}