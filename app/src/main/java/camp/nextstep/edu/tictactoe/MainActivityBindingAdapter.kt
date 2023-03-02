package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.nextstep.edu.tictactoe.domain.Cell

@BindingAdapter("tictactoe_cell")
fun setImageViewResource(imageView: ImageView, cellType: Cell) {
    when (cellType) {
        Cell.X -> {
            imageView.setImageResource(R.drawable.ic_x_black)
        }
        Cell.O -> {
            imageView.setImageResource(R.drawable.ic_o_black)
        }
        Cell.NONE -> {
            imageView.setImageResource(R.color.transparent)
        }
    }
}
