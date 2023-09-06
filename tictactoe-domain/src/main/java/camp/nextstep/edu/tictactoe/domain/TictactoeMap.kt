package camp.nextstep.edu.tictactoe.domain

import javax.inject.Inject

class TictactoeMap @Inject constructor(
    private var _positions: MutableMap<CellPosition, Owner>
) {
    val positions: Map<CellPosition, Owner>
        get() = _positions

    fun set(position: CellPosition, isXTurn: Boolean) {
        check(_positions[position] == Owner.NONE) {
            "선택한 곳에 또 입력할 수 없음"
        }
        _positions[position] = if (isXTurn) {
            Owner.X
        } else {
            Owner.O
        }
    }

    fun reset() {
        _positions.replaceAll { _, _ -> Owner.NONE }
    }
}
