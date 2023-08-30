package camp.nextstep.edu.tictactoe.domain

import org.junit.Assert.assertThrows
import org.junit.Test

class TictactoeMapTest {

    @Test
    fun `입력한 곳에 또 입력할 수 없음`() {
        // given
        val tictactoeMap = TictactoeMap()
        tictactoeMap.set(CellPosition.TOP_LEFT, true)

        // then
        assertThrows(IllegalStateException::class.java) {
            // when 같은 자리에 입력
            tictactoeMap.set(CellPosition.TOP_LEFT, false)
        }
    }
}
