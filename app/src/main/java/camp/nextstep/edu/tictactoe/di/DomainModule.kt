package camp.nextstep.edu.tictactoe.di

import com.example.tictectoe_domain.Board
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.TictectoeRule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    companion object {

        @Provides
        @Singleton
        fun provideBoard(): Board {
            return Board.EMPTY
        }

        @Provides
        @Singleton
        fun provideTictectoeRule(): TictectoeRule {
            return TictectoeRule()
        }

        @Provides
        @Singleton
        fun provideGame(board: Board, rule: TictectoeRule): Game {
            return Game(board, rule)
        }
    }

}
