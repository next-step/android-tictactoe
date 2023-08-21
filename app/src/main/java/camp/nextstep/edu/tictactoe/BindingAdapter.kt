package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import camp.nextstep.edu.tictactoe.domain.Block
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("setImgRes")
    @JvmStatic
    fun setImgRes(view: ImageView, block: Block) {
        when (block) {
            Block.BlockNon -> view.setImageResource(android.R.color.transparent)
            Block.BlockX -> view.setImageResource(R.drawable.ic_x_black)
            Block.BlockO -> view.setImageResource(R.drawable.ic_o_black)
        }
    }
}
