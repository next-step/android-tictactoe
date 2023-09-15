package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.tictectoe_domain.Cell

@BindingAdapter("imgRes")
fun loadImage(imageView: ImageView, cell: Cell) {
    when (cell) {
        is Cell.PLAYER1 -> {
            imageView.setImageResource(R.drawable.ic_x_black)
        }

        is Cell.PLAYER2 -> {
            imageView.setImageResource(R.drawable.ic_o_black)
        }

        is Cell.NONE -> {
            imageView.setImageResource(0)
        }
    }
}
