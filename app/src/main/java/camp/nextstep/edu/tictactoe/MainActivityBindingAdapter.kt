package camp.nextstep.edu.tictactoe


import android.graphics.drawable.ShapeDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.domain.model.Cell


@BindingAdapter("bind:drawOorXWithPoint")
fun setImageResource(view: ImageView, cell: Cell) {
    when (cell) {
        is Cell.X -> view.setImageResource(R.drawable.ic_x_black)
        is Cell.O -> view.setImageResource(R.drawable.ic_o_black)
        is Cell.Empty -> {
            val transparentDrawable = ShapeDrawable()
            transparentDrawable.alpha = 0
            view.setImageDrawable(transparentDrawable)
        }
    }

}
