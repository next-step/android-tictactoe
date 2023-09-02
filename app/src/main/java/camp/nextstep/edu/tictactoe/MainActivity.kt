package camp.nextstep.edu.tictactoe

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import com.example.tictectoe_domain.Game
import com.example.tictectoe_domain.Cell

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TictactoeViewModel by viewModels{ TictactoeViewModelFactory(Game()) }

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
        viewModel.toastEvent.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_two -> Toast.makeText(this, "TODO: 2인 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_random -> Toast.makeText(this, "TODO: 랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_draw -> Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun getXOImgResource(player: Cell): Drawable {
        return when (player) {
            Cell.PLAYER1 ->  ContextCompat.getDrawable(this, R.drawable.ic_x_black)!!
            Cell.PLAYER2 ->  ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
            else ->  ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
        }
    }
}
