package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityTictactocBinding

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

        observerToastMessage()
    }

    private fun observerToastMessage() {
        viewModel.tictactocToastMessage.observe(this) { toastMessage ->
            when (toastMessage) {
                TictactocToastMessage.WrongClick -> showToastMessage(R.string.wrong_click)
                TictactocToastMessage.GameOver -> showToastMessage(R.string.game_over)
                TictactocToastMessage.XWin -> showToastMessage(R.string.x_win)
                TictactocToastMessage.OWin -> showToastMessage(R.string.o_win)
                TictactocToastMessage.Tie -> showToastMessage(R.string.tie)
            }
        }
    }

    private fun showToastMessage(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two ->
                Toast.makeText(this, "TODO: 2인 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_random ->
                Toast.makeText(this, "TODO: 랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
