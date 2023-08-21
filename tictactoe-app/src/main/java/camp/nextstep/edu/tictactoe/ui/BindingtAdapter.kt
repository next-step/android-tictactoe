package camp.nextstep.edu.tictactoe.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.edu.tictactoe.R
import camp.nextstep.edu.tictactoe.domain.CellPosition

object BindingtAdapter {

    @BindingAdapter(value = ["position", "data"], requireAll = false)
    @JvmStatic
    fun setImage(
        imageView: ImageView,
        position: CellPosition,
        map: HashMap<CellPosition, Boolean>?
    ) {
        if (map == null) return
        val data = map[position]
        if (data != null) {
            if (data) {
                imageView.setImageResource(R.drawable.ic_x_black)
            } else {
                imageView.setImageResource(R.drawable.ic_o_black)
            }
        } else {
            imageView.setImageResource(0)
        }
    }
}
