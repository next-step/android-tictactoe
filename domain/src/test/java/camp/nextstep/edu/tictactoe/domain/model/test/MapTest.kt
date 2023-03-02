package camp.nextstep.edu.tictactoe.domain.model.test

import camp.nextstep.edu.tictactoe.domain.model.Map
import org.junit.Test

class MapTest {

    //9개의 칸을 만들지 않으면 예외를 발생 시킨다.
    @Test
    fun `9개 칸에 빈 셀을 만든다`() {
        Map.EMPTY
    }
}