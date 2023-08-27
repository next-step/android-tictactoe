package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.Tictactoe
import com.nextstep.edu.tictactoe.domain.TictactoeImpl
import com.nextstep.edu.tictactoe.domain.RandomStrategy
import com.nextstep.edu.tictactoe.domain.mode.PlayerTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomMiddleTictactoeImpl
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoe
import com.nextstep.edu.tictactoe.domain.mode.RandomNormalTictactoeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TictactocModule {

    private fun provideDefaultRandomStrategy(): RandomStrategy = DefaultRandomStrategy()

    private fun provideRandomNormalTictactocImpl(): RandomNormalTictactoe =
        RandomNormalTictactoeImpl(provideDefaultRandomStrategy())

    private fun provideRandomMiddleTictactocImpl(): RandomMiddleTictactoe =
        RandomMiddleTictactoeImpl(provideDefaultRandomStrategy())

    @Provides
    fun providePlayerTictactoc(): Tictactoe = TictactoeImpl(PlayerTictactoeImpl())

    @Provides
    fun provideRandomNormalTictactoc(): Tictactoe =
        TictactoeImpl(provideRandomNormalTictactocImpl())

    @Provides
    fun provideRandomMiddleTictactoc(): Tictactoe =
        TictactoeImpl(provideRandomMiddleTictactocImpl())


}