package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.edu.tictactoe.domain.Mode
import camp.nextstep.edu.tictactoe.domain.di.DomainModule

class TicTacToeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TicTacToeViewModel by viewModels {
        TicTacToeViewModelFactory(
            DomainModule.provideTicTacToeManager(),
            DomainModule.provideTicTacToe(Mode.RANDOM)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initObserver()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)
    }

    private fun initObserver() {
        viewModel.uiEffect.observe(this) { uiEffect ->
            when(uiEffect) {
                is UiEffect.ShowToast -> {
                    Toast.makeText(this, uiEffect.message, Toast.LENGTH_SHORT).show()
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
                viewModel.changeMode(Mode.PLAYER)
            }
            R.id.menu_random -> {
                viewModel.changeMode(Mode.RANDOM)
            }
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
