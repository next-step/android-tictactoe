package camp.nextstep.edu.tictactoe.domain

class Blocks(private val blockSize: Int) {

    private val blocks = Array(blockSize) { Array(blockSize) { Block.BlockNon } }

    fun setItem(position: Int, block: Block): Block {
        val row = position / blockSize
        val col = position % blockSize
        return setItem(row, col, block)
    }

    private fun setItem(row: Int, col: Int, block: Block): Block {
        if (blocks[row][col] == Block.BlockNon) {
            blocks[row][col] = block
        }
        return blocks[row][col]
    }

    fun checkRow(position: Int, block: Block): Boolean {
        val row = position / blockSize

        return blocks[row].all { it == block }
    }

    fun checkColumn(position: Int, block: Block): Boolean {
        val col = position % blockSize

        return blocks.all { it[col] == block }
    }

    fun checkDiagonals(position: Int, block: Block): Boolean {
        val row = position / blockSize
        val col = position % blockSize

        if (!checkDiagonalPosition(row, col)) return false

        val diagonalItems = (0 until blockSize).map { blocks[it][it] }
        val reverseDiagonalItems = (0 until blockSize).map { blocks[it][blockSize - 1 - it] }

        return diagonalItems.all { it == block } || reverseDiagonalItems.all { it == block }
    }

    private fun checkDiagonalPosition(row: Int, col: Int): Boolean {
        return row == col || row == (blockSize - 1 - col)
    }

    fun getBlocks(): List<Block> {
        return blocks.flatten()
    }

    fun reset() {
        for (row in blocks) {
            row.fill(Block.BlockNon)
        }
    }
}
