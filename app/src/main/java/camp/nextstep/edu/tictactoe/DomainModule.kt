package camp.nextstep.edu.tictactoe

import camp.nextstep.tictactoe.domain.DefaultTicTaeToHandler
import camp.nextstep.tictactoe.domain.TicTaeToHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

	@Provides
	fun providesTicTaeTocHandler(): TicTaeToHandler {
		return DefaultTicTaeToHandler()
	}
}