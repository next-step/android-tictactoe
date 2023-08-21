package camp.nextstep.edu.tictactoe.domain

class Tictactoe(
    private val blocks: Blocks = Blocks(BLOCK_SIZE)
) {

    fun setItem(position: Int, block: Block): Block {
        return blocks.setItem(position, block)
    }

    fun checkWin(position: Int, block: Block): Boolean {
        return blocks.checkColumn(position, block) ||
            blocks.checkRow(position, block) ||
            blocks.checkDiagonals(position, block)
    }

    fun checkDraw(): Boolean {
        return blocks.getBlocks().none { it == Block.BlockNon }
    }

    fun getBlocks(): List<Block> {
        return blocks.getBlocks()
    }

    fun restart() {
        blocks.reset()
    }

    companion object {
        const val BLOCK_SIZE = 3
    }
}
