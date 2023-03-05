package camp.nextstep.edu.tictactoe


import android.graphics.drawable.ShapeDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.model.TurnState


@BindingAdapter("bind:drawOorXWithPoint")
fun setImageResource(view: ImageView, turn: TurnState) {
    when (turn) {
        TurnState.X -> view.setImageResource(R.drawable.ic_x_black)
        TurnState.O -> view.setImageResource(R.drawable.ic_o_black)
        TurnState.EMPTY -> {
            val transparentDrawable = ShapeDrawable()
            transparentDrawable.alpha = 0
            view.setImageDrawable(transparentDrawable)
        }
    }

}
