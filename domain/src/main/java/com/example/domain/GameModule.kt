package com.example.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GameModule {

    @Provides
    fun provideTurn(): Int {
        return 0
    }

    @Provides
    fun provideBoardState(): BoardState {
        return BoardState()
    }

    @Provides
    fun provideGame(turn: Int, boardState: BoardState): Game {
        return GameImpl(Turn(turn), boardState)
    }
}
