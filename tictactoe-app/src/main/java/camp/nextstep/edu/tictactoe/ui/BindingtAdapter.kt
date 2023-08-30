package camp.nextstep.edu.tictactoe.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.R
import camp.nextstep.edu.tictactoe.domain.CellPosition
import camp.nextstep.edu.tictactoe.domain.Owner
import camp.nextstep.edu.tictactoe.domain.TictactoeMap

object BindingtAdapter {

    @BindingAdapter(value = ["owner"], requireAll = true)
    @JvmStatic
    fun setMark(
        imageView: ImageView,
        owner: Owner
    ) {
        if (owner == null) return
        when (owner) {
            Owner.X -> imageView.setImageResource(R.drawable.ic_x_black)
            Owner.O -> imageView.setImageResource(R.drawable.ic_o_black)
            Owner.NONE -> imageView.setImageResource(0)
        }
    }

    @BindingAdapter(value = ["position", "data"], requireAll = true)
    @JvmStatic
    fun setMark(
        imageView: ImageView,
        position: CellPosition,
        tictactoeMap: TictactoeMap
    ) {
        if (tictactoeMap == null) return
        val data = tictactoeMap.positions[position] ?: return
        when (data) {
            Owner.X -> imageView.setImageResource(R.drawable.ic_x_black)
            Owner.O -> imageView.setImageResource(R.drawable.ic_o_black)
            Owner.NONE -> imageView.setImageResource(0)
        }
    }
}
