package camp.nextstep.edu.tictactoe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Marker

@BindingAdapter("app:markerSrc")
fun setMarkerImageResource(imageView: ImageView, marker: Marker?) {
	when (marker) {
		Marker.X -> imageView.setImageResource(R.drawable.ic_x_black)
		Marker.O -> imageView.setImageResource(R.drawable.ic_o_black)
		else -> imageView.setImageResource(0)
	}
}

@BindingAdapter("app:isImageClickable")
fun setImageClickable(imageView: ImageView, gameStatus: GameStatus) {
	return when (gameStatus) {
		is GameStatus.InProgress -> imageView.isClickable = true
		is GameStatus.End, is GameStatus.Draw -> imageView.isClickable = false
	}
}