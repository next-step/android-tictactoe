package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.DefaultRandomStrategy
import com.nextstep.edu.tictactoe.domain.DefaultTictactoe
import com.nextstep.edu.tictactoe.domain.DefaultTictactoeImpl
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
    fun providePlayerTictactoc(): DefaultTictactoe = DefaultTictactoeImpl(PlayerTictactoeImpl())

    @Provides
    fun provideRandomNormalTictactoc(): DefaultTictactoe =
        DefaultTictactoeImpl(provideRandomNormalTictactocImpl())

    @Provides
    fun provideRandomMiddleTictactoc(): DefaultTictactoe =
        DefaultTictactoeImpl(provideRandomMiddleTictactocImpl())


}