package camp.nextstep.edu.tictactoe.domain.di

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeGame
import camp.nextstep.edu.tictactoe.domain.TictactoeMap
import camp.nextstep.edu.tictactoe.domain.strategy.IntermediateStrategy
import camp.nextstep.edu.tictactoe.domain.strategy.TictactoeStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideTictactoeGame(
        strategy: TictactoeStrategy,
        tictactoeMap: TictactoeMap
    ): TictactoeGame {
        return TictactoeGame(strategy, tictactoeMap)
    }

    @Provides
    fun provideTictactoeStrategy(): TictactoeStrategy {
        return IntermediateStrategy()
    }

    @Provides
    fun provideTictactoeMap(
        positions: MutableMap<CellPosition, Owner>
    ): TictactoeMap {
        return TictactoeMap(positions)
    }

    @Provides
    fun providePositions(): MutableMap<CellPosition, Owner> {
        return CellPosition.values().associateWith {
            Owner.NONE
        }.toMutableMap()
    }
}
