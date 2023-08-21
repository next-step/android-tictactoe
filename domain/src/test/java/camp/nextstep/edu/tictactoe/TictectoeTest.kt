package camp.nextstep.edu.tictactoe

import camp.nextstep.edu.tictactoe.domain.Block
import camp.nextstep.edu.tictactoe.domain.Tictactoe
import com.google.common.truth.Truth
import org.junit.Test

class TictectoeTest {
    private val tictactoe = Tictactoe()

    @Test
    fun `x설정 확인`() {
        val actual = tictactoe.setItem(0, Block.BlockX)

        Truth.assertThat(actual).isEqualTo(Block.BlockX)
    }

    @Test
    fun `5번쨰 포지션 x설정`() {
        val actual = tictactoe.setItem(5, Block.BlockX)

        Truth.assertThat(actual).isEqualTo(Block.BlockX)
    }

    @Test
    fun `x설정된 경우 o를 설정할 수 없다`() {
        tictactoe.setItem(0, Block.BlockX)

        val actual = tictactoe.setItem(0, Block.BlockO)

        Truth.assertThat(actual).isNotEqualTo(Block.BlockO)
    }

    @Test
    fun `o설정된 경우 x를 설정할 수 없다`() {
        tictactoe.setItem(0, Block.BlockO)

        val actual = tictactoe.setItem(0, Block.BlockX)

        Truth.assertThat(actual).isNotEqualTo(Block.BlockX)
    }

    @Test
    fun `0 포지션 x 설정된 경우 가로 승리 실패`() {
        tictactoe.setItem(0, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isFalse()
    }

    @Test
    fun `0,1,2 포지션 x 설정된 경우 가로 승리`() {
        tictactoe.setItem(0, Block.BlockX)
        tictactoe.setItem(1, Block.BlockX)
        tictactoe.setItem(2, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isTrue()
    }

    @Test
    fun `0 포지션 x 설정된 경우 세로 승리 실패`() {
        tictactoe.setItem(0, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isFalse()
    }

    @Test
    fun `0,3,6 포지션 x 설정된 경우 세로 승리`() {
        tictactoe.setItem(0, Block.BlockX)
        tictactoe.setItem(3, Block.BlockX)
        tictactoe.setItem(6, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isTrue()
    }

    @Test
    fun `0 포지션 x 설정된 경우 대각선 승리 실패`() {
        tictactoe.setItem(0, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isFalse()
    }

    @Test
    fun `0,4,8 포지션 x 설정된 경우 대각선 승리`() {
        tictactoe.setItem(0, Block.BlockX)
        tictactoe.setItem(4, Block.BlockX)
        tictactoe.setItem(8, Block.BlockX)

        val actual = tictactoe.checkWin(0, Block.BlockX)

        Truth.assertThat(actual).isTrue()
    }

    @Test
    fun `2 포지션 x 설정된 경우 대각선 승리 실패`() {
        tictactoe.setItem(2, Block.BlockX)

        val actual = tictactoe.checkWin(2, Block.BlockX)

        Truth.assertThat(actual).isFalse()
    }

    @Test
    fun `2,4,6 포지션 x 설정된 경우 대각선 승리`() {
        tictactoe.setItem(2, Block.BlockX)
        tictactoe.setItem(4, Block.BlockX)
        tictactoe.setItem(6, Block.BlockX)

        val actual = tictactoe.checkWin(2, Block.BlockX)

        Truth.assertThat(actual).isTrue()
    }
}
