package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.edu.tictactoe.domain.TictactoeStatus
import camp.nextstep.edu.tictactoe.viewmodel.GameResult
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
            val message = when (it) {
                is GameResult.Status -> getMessage(it.status)
                is GameResult.Fail -> it.message
            }
            if (message.isNullOrEmpty()) {
                return@observe
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
