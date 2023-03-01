package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.edu.tictactoe.model.TurnResultMessage
import camp.nextstep.edu.tictactoe.model.TurnResultMessage.ErrorMessage
import camp.nextstep.edu.tictactoe.model.TurnResultMessage.GameResultMessage

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MainActivity
        }
        observerViewMode()
    }

    private fun observerViewMode() {
        viewModel.showToast.observe(this) {
            showTurnResultMessage(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun showTurnResultMessage(message: TurnResultMessage) {
        when (message) {
            is ErrorMessage.WrongClick -> Toast.makeText(this, message.message, Toast.LENGTH_SHORT)
                .show()
            is ErrorMessage.FinishGame -> Toast.makeText(
                this,
                message.message,
                Toast.LENGTH_SHORT
            ).show()
            is GameResultMessage.OWin -> Toast.makeText(this, message.message, Toast.LENGTH_SHORT)
                .show()
            is GameResultMessage.XWin -> Toast.makeText(this, message.message, Toast.LENGTH_SHORT)
                .show()
            is GameResultMessage.Tie -> Toast.makeText(this, message.message, Toast.LENGTH_SHORT)
                .show()

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> {
                Toast.makeText(this, "TODO: 2인 모드로 전환", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_random -> {
                Toast.makeText(this, "TODO: 랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()

        }
        return true
    }
}
