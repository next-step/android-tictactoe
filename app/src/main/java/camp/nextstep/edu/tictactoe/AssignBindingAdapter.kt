package camp.nextstep.edu.tictactoe

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.domain.Block
import com.example.domain.OBlock
import com.example.domain.XBlock

@BindingAdapter("android:srcChanged")
fun setAssign(view: View?, block: Block?) {
    when (block) {
        XBlock -> view?.setBackgroundResource(R.drawable.ic_x_black)
        OBlock -> view?.setBackgroundResource(R.drawable.ic_o_black)
        else -> view?.setBackgroundResource(0)
    }
}
