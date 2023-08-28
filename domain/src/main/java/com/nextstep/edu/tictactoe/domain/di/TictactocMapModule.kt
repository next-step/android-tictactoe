package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import com.nextstep.edu.tictactoe.domain.model.TictactoeMapImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TictactocMapModule {

    @Binds
    @Singleton
    internal abstract fun provideTictactocMap(
        tictactoeMapImpl: TictactoeMapImpl
    ): TictactoeMap
}