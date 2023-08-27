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
import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TictactocModule {

    private fun provideDefaultRandomStrategy(): RandomStrategy = DefaultRandomStrategy()

    @Provides
    fun providePlayerTictactoc(
        tictactoeMap: TictactoeMap
    ): Tictactoe = TictactoeImpl(
        strategy = PlayerTictactoeImpl(tictactoeMap),
        tictactoeMap = tictactoeMap
    )

    @Provides
    fun provideRandomNormalTictactoc(
        tictactoeMap: TictactoeMap
    ): Tictactoe =
        TictactoeImpl(
            strategy = RandomNormalTictactoeImpl(
                randomStrategy = provideDefaultRandomStrategy(),
                tictactoeMap = tictactoeMap
            ),
            tictactoeMap = tictactoeMap
        )

    @Provides
    fun provideRandomMiddleTictactoc(
        tictactoeMap: TictactoeMap
    ): Tictactoe =
        TictactoeImpl(
            strategy = RandomMiddleTictactoeImpl(
                randomStrategy = provideDefaultRandomStrategy(),
                tictactoeMap = tictactoeMap
            ),
            tictactoeMap = tictactoeMap
        )


}