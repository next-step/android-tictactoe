package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.tictectoe_domain.Cell

object ImgBindingAdapter {
    @BindingAdapter("imgRes")
    @JvmStatic
    fun loadImage(imageView: ImageView, cell: Cell) {
        when (cell) {
            Cell.PLAYER1 -> {
                imageView.setImageResource(R.drawable.ic_x_black)
            }
            Cell.PLAYER2 -> {
                imageView.setImageResource(R.drawable.ic_o_black)
            }
            Cell.NONE -> {
                imageView.setImageResource(0)
            }
        }
    }
}
