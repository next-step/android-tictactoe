package camp.nextstep.edu.tictactoe.domain

import camp.nextstep.edu.tictactoe.domain.di.DomainModule
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class TictactoeMapTest {

    private lateinit var tictactoeMap: TictactoeMap

    @Before
    fun setUp() {
        tictactoeMap = TictactoeMap(DomainModule.providePositions())
    }

    @Test
    fun `입력한 곳에 또 입력할 수 없음`() {
        // when
        tictactoeMap.set(CellPosition.TOP_LEFT, true)

        // then
        assertThrows(IllegalStateException::class.java) {
            // when 같은 자리에 입력
            tictactoeMap.set(CellPosition.TOP_LEFT, false)
        }
    }
}
