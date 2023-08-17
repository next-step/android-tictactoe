package camp.nextstep.edu.tictactoe

import android.view.View
import androidx.databinding.BindingAdapter
import com.nextstep.edu.tictactoe.domain.model.Turn

@BindingAdapter("drawPoint")
fun bindDrawPoint(view: View, turn: Turn?) {
    turn?.let {
        when (turn) {
            Turn.X -> view.setBackgroundResource(R.drawable.ic_x_black)
            Turn.O -> view.setBackgroundResource(R.drawable.ic_o_black)
            Turn.UNKNOWN -> view.setBackgroundResource(0)
        }
    }
}