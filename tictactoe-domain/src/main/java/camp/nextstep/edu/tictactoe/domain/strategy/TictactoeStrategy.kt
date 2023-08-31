package camp.nextstep.edu.tictactoe.domain.strategy

import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

interface TictactoeStrategy {

    fun getPosition(tictactoeMap: TictactoeMap): CellPosition?
}
