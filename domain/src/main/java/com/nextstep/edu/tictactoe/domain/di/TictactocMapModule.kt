package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import com.nextstep.edu.tictactoe.domain.model.TictactoeMapImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TictactocMapModule {

    @Provides
    @Singleton
    fun provideTictactocMap(): TictactoeMap = TictactoeMapImpl()
}