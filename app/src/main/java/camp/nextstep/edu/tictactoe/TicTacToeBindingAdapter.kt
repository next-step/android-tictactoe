package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object TicTacToeBindingAdapter {


    @JvmStatic
    @BindingAdapter("ox")
    fun setImageResources(imageView: ImageView, ox: OX?) {
        val drawableId = when (ox) {
            OX.X -> R.drawable.ic_x_black
            OX.O -> R.drawable.ic_o_black
            else -> 0
        }

        imageView.setImageResource(drawableId)
    }
}