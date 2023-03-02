package camp.nextstep.edu.tictactoe


import android.graphics.drawable.ShapeDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.model.TurnState


@BindingAdapter("bind:drawOorXWithPoint")
fun setImageResource(view: ImageView, turn: TurnState) {
    if (turn == TurnState.X) {
        view.setImageResource(R.drawable.ic_x_black)
    } else if (turn == TurnState.O) {
        view.setImageResource(R.drawable.ic_o_black)
    } else {
        val transparentDrawable = ShapeDrawable()
        transparentDrawable.alpha = 0
        view.setImageDrawable(transparentDrawable)
    }
}