package camp.nextstep.edu.tictactoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import camp.nextstep.edu.tictactoe.domain.Block
import camp.nextstep.edu.tictactoe.domain.Tictactoe

class MainViewModel(
    private val tictactoe: Tictactoe = Tictactoe()
) : ViewModel() {

    private val _blockChecks = MutableLiveData(tictactoe.getBlocks())

    val blockChecks: LiveData<List<Block>>
        get() = _blockChecks

    private val _event: MutableLiveData<Event?> = MutableLiveData()

    val event: LiveData<Event?>
        get() = _event

    private var currentBlock = Block.BlockX

    fun clickBlock(position: Int) {
        if (_event.value != null) {
            _event.value = _event.value
            return
        }

        val setItem = tictactoe.setItem(position, currentBlock)

        val blocks: List<Block> = tictactoe.getBlocks()
        _blockChecks.value = blocks

        showMessage(position)

        currentBlock = if (setItem == Block.BlockX) Block.BlockO else Block.BlockX
    }

    private fun showMessage(position: Int) {
        if (tictactoe.checkWin(position, currentBlock)) {
            showWinMessage()
        }

        if (tictactoe.checkDraw()) {
            _event.value = Event.EVENT_DRAW
        }
    }

    private fun showWinMessage() {
        when (currentBlock) {
            Block.BlockO -> _event.value = Event.EVENT_WIN_O
            Block.BlockX -> _event.value = Event.EVENT_WIN_X
            Block.BlockNon -> {}
        }
    }

    fun clickRestart() {
        tictactoe.restart()

        val blocks: List<Block> = tictactoe.getBlocks()
        _blockChecks.value = blocks
        _event.value = null

        currentBlock = Block.BlockX
    }

    enum class Event {
        EVENT_WIN_X, EVENT_WIN_O, EVENT_DRAW
    }
}
