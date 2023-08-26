package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.tictactoe.domain.GameStatus
import camp.nextstep.tictactoe.domain.Mode
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val mainViewModel: MainViewModel by viewModels {
		ViewModelFactory()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.lifecycleOwner = this
		binding.viewModel = mainViewModel

		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.RESUMED) {
				mainViewModel.gameStatus.collect { gameStatus ->
					when (gameStatus) {
						is GameStatus.End -> Toast.makeText(this@MainActivity, "${gameStatus.winnerMarker} 승리", Toast.LENGTH_LONG).show()
						is GameStatus.Draw -> Toast.makeText(this@MainActivity, "무승부", Toast.LENGTH_LONG).show()
						else -> Unit
					}
				}
			}
		}
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.main, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.menu_two -> {
				mainViewModel.updateMode(Mode.TwoPerson)
				Toast.makeText(this, "TODO: 2인 모드로 전환", Toast.LENGTH_SHORT).show()
			}

			R.id.menu_random -> {
				mainViewModel.updateMode(Mode.Random)
				Toast.makeText(this, "TODO: 랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
			}

			R.id.menu_intermediate -> {
				mainViewModel.updateMode(Mode.Intermediate)
				Toast.makeText(this, "TODO: 중급 모드로 전환", Toast.LENGTH_SHORT).show()
			}
		}
		return true
	}
}
