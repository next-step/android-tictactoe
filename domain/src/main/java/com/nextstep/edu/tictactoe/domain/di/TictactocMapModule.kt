package com.nextstep.edu.tictactoe.domain.di

import com.nextstep.edu.tictactoe.domain.model.TictactoeMap
import com.nextstep.edu.tictactoe.domain.model.TictactoeMapImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TictactocMapModule {

    @Provides
    fun provideTictactocMap(): TictactoeMap = TictactoeMapImpl()
}