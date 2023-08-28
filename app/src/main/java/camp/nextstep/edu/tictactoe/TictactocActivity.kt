package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityTictactocBinding
import com.nextstep.edu.tictactoe.domain.model.GameMode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TictactocActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTictactocBinding
    private val viewModel: TictactocViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTictactocBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = this@TictactocActivity
        }
        setContentView(binding.root)
        viewModel.onSetGameMode(gameMode = GameMode.RANDOM_MIDDLE)
        observerToastMessage()
    }

    private fun observerToastMessage() {
        viewModel.tictactocToastMessage.observe(this) { event ->
            event.consume()?.let {
                showToastMessage(it.resId)
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToastMessage(@StringRes resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> {
                showToastMessage(R.string.game_mode_two_player)
                viewModel.onSetGameMode(gameMode = GameMode.TWO_PLAYER)
            }
            R.id.menu_random -> {
                showToastMessage(R.string.game_mode_random_player)
                viewModel.onSetGameMode(gameMode = GameMode.RANDOM)
            }
            R.id.menu_random_middle -> {
                showToastMessage(R.string.game_mode_ramdom_middle_player)
                viewModel.onSetGameMode(gameMode = GameMode.RANDOM_MIDDLE)
            }
        }
        return true
    }
}
