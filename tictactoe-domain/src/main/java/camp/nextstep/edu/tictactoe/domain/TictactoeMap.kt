package camp.nextstep.edu.tictactoe.domain

class TictactoeMap {

    private var _positions: MutableMap<CellPosition, Owner> = initPositions()
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

    private fun initPositions(): MutableMap<CellPosition, Owner> {
        return CellPosition.values().associateWith {
            Owner.NONE
        }.toMutableMap()
    }
}
