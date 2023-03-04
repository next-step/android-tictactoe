package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import com.example.domain.DrawMode
import com.example.domain.GameStatus
import com.example.domain.RandomMode
import com.example.domain.TwoPlayerMode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TicTacToeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observeException()
        observeGameState()
        observeGameMode()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two ->
                viewModel.changeMode(TwoPlayerMode)
            R.id.menu_random ->
                viewModel.changeMode(RandomMode())
            R.id.menu_draw ->
                viewModel.changeMode(DrawMode())
        }
        return true
    }

    private fun init() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun observeException() {
        viewModel.exceptionMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeGameMode() {
        viewModel.mode.observe(this) {
            when (it) {
                is TwoPlayerMode -> Toast.makeText(this, "2인 모드", Toast.LENGTH_SHORT).show()
                is RandomMode -> Toast.makeText(this, "랜덤 모드", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun observeGameState() {
        viewModel.state.observe(this) {
            when (it.status) {
                GameStatus.DRAW -> Toast.makeText(this, "무승부", Toast.LENGTH_SHORT).show()
                GameStatus.O_WON -> Toast.makeText(this, "O 승리", Toast.LENGTH_SHORT).show()
                GameStatus.X_WON -> Toast.makeText(this, "X 승리", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }
}
