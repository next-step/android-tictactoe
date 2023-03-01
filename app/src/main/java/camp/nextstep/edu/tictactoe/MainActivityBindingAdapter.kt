package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("tictactoe_cell")
fun setImageViewResource(imageView: ImageView, isFirst: Boolean?) {
    when (isFirst) {
        true -> {
            imageView.setImageResource(R.drawable.ic_x_black)
        }
        false -> {
            imageView.setImageResource(R.drawable.ic_o_black)
        }
        else -> {
            imageView.setImageResource(R.color.transparent)
        }
    }
}
