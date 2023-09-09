package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.GameMode
import com.example.tictectoe_domain.GameStatus

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TictactoeViewModel by viewModels { TictactoeViewModelFactory(Game()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initViewModel()
        initObserve()

        setContentView(binding.root)
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initObserve() {
        viewModel.gameStatus.observe(this) { gameStatus ->
            val msgId = when (gameStatus) {
                GameStatus.PLAYER1_WIN -> {
                    R.string.msg_player1_win
                }

                GameStatus.PLAYER2_WIN -> {
                    R.string.msg_player2_win
                }

                GameStatus.DRAW_GAME -> {
                    R.string.msg_draw
                }

                else -> {
                    return@observe
                }
            }
            Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> {
                Toast.makeText(this, "2인 모드로 전환", Toast.LENGTH_SHORT).show()
                viewModel.changeGameMode(GameMode.TWO_PLAYER_MODE)
            }

            R.id.menu_random -> {
                Toast.makeText(this, "랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
                viewModel.changeGameMode(GameMode.RANDOM_MODE)
            }

            R.id.menu_intermediate_level -> {
                Toast.makeText(this, "중급 모드로 전환", Toast.LENGTH_SHORT).show()
                viewModel.changeGameMode(GameMode.INTERMEDIATE_LEVEL_MODE)
            }
        }
        return true
    }
}
