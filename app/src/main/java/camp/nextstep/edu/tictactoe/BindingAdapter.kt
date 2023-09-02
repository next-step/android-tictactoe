/**
 * @author Daewon on 02,September,2023
 *
 */

package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.domain.Cell
import camp.nextstep.edu.tictactoe.domain.Position


@BindingAdapter(value = ["app:position", "app:uiState"], requireAll = true)
fun setImageResource(imageView: ImageView, position: Position, uiState: UiState) {
    setTicTacToeImageResource(imageView, uiState.board[position])
    when(uiState) {
        is UiState.Inprogress -> {
            imageView.isClickable = true
        }
        is UiState.End, is UiState.Draw -> {
           imageView.isClickable = false
        }
    }
}

private fun setTicTacToeImageResource(imageView: ImageView, cell: Cell) {
    when (cell) {
        is Cell.X -> imageView.setImageResource(R.drawable.ic_x_black)
        is Cell.O -> imageView.setImageResource(R.drawable.ic_o_black)
        else -> imageView.setImageResource(0)
    }
}
