package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setContentView(binding.root)
        observeEvent()
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun observeEvent() {
        viewModel.event.observe(this) { eventType ->
            val event: MainViewModel.Event = eventType ?: return@observe

            val message = when (event) {
                MainViewModel.Event.EVENT_WIN_X -> {
                    getString(R.string.message_win, "X")
                }
                MainViewModel.Event.EVENT_WIN_O -> {
                    getString(R.string.message_win, "O")
                }
                MainViewModel.Event.EVENT_DRAW -> {
                    getString(R.string.message_draw)
                }
            }

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
