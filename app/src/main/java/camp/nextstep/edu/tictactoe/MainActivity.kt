package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        addObserver()
        changeGameMode(GameMode.RANDOM)
    }

    private fun init() {
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
    }

    private fun addObserver() {
        mainViewModel.gameStatus.observe(this) { gameStatus ->
            when (gameStatus) {
                TicTacToeStatus.DRAW -> showToastMessage(getString(R.string.draw))
                TicTacToeStatus.O_WIN -> showToastMessage(getString(R.string.winner, OX.O.name))
                TicTacToeStatus.X_WIN -> showToastMessage(getString(R.string.winner, OX.X.name))
                else -> Unit
            }
        }

        mainViewModel.errorMessage.observe(this) { errorMessage ->
            showToastMessage(errorMessage)
        }
    }

    private fun changeGameMode(gameMode: GameMode) {
        mainViewModel.setGameMode(gameMode)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> changeGameMode(GameMode.TWO_PLAYERS)
            R.id.menu_random -> changeGameMode(GameMode.RANDOM)
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
