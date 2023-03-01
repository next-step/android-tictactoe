package camp.nextstep.edu.tictactoe


import android.graphics.drawable.ShapeDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("bind:drawOorXWithPoint")
fun setImageResource(view: ImageView, point: Boolean?) {
    if (point == true) {
        view.setImageResource(R.drawable.ic_x_black)
    } else if (point == false) {
        view.setImageResource(R.drawable.ic_o_black)
    } else {
        val transparentDrawable = ShapeDrawable()
        transparentDrawable.alpha = 0
        view.setImageDrawable(transparentDrawable)
    }
}