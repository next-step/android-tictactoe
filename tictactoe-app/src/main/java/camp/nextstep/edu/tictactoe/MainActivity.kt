package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
import camp.nextstep.edu.tictactoe.mode.Mode
import camp.nextstep.edu.tictactoe.viewmodel.GameResultUiState
import camp.nextstep.edu.tictactoe.viewmodel.TictactoeViewModel
import camp.nextstep.edu.tictactoe.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TictactoeViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.model = viewModel

        viewModel.uiState.observe(this) {
            when (it) {
                is GameResultUiState.Status -> processStatus(it.status)
                is GameResultUiState.Fail -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun processStatus(status: TictactoeStatus) {
        when (status) {
            TictactoeStatus.Draw, TictactoeStatus.XWin, TictactoeStatus.OWin -> {
                Toast.makeText(this, getMessage(status), Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    private fun getMessage(status: TictactoeStatus): String {
        return when (status) {
            TictactoeStatus.Draw -> "무승부"
            TictactoeStatus.OWin -> "O 승리"
            TictactoeStatus.XWin -> "X 승리"
            else -> ""
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> {
                viewModel.changeMode(Mode.TWO_PLAYERS)
                Toast.makeText(this, "2인 모드로 전환", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_random -> {
                viewModel.changeMode(Mode.RANDOM)
                Toast.makeText(this, "랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
