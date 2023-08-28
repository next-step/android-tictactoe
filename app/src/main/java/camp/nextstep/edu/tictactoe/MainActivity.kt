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
import com.example.tictectoe_domain.TictectoeBoard
import com.example.tictectoe_domain.Player
import com.example.tictectoe_domain.TictectoeRule

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TictactoeViewModel by viewModels{ TictactoeViewModelFactory(TictectoeBoard(), TictectoeRule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        initViewModel()
        initObserve()
        initBoard()

        setContentView(binding.root)
    }

    private fun initViewModel() {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initObserve() {
        viewModel.clickBoard.observe(this) {
            when(it.position) {
                1 -> binding.cellTopLeft.setImageDrawable(getXOImgResource(it.player))
                2 -> binding.cellTop.setImageDrawable(getXOImgResource(it.player))
                3 -> binding.cellTopRight.setImageDrawable(getXOImgResource(it.player))
                4 -> binding.cellMiddleLeft.setImageDrawable(getXOImgResource(it.player))
                5 -> binding.cellMiddle.setImageDrawable(getXOImgResource(it.player))
                6 -> binding.cellMiddleRight.setImageDrawable(getXOImgResource(it.player))
                7 -> binding.cellBottomLeft.setImageDrawable(getXOImgResource(it.player))
                8 -> binding.cellBottom.setImageDrawable(getXOImgResource(it.player))
                9 -> binding.cellBottomRight.setImageDrawable(getXOImgResource(it.player))
            }
        }

        viewModel.toastEvent.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initBoard() {
        viewModel.clickRestart.observe(this) {
            binding.cellTopLeft.setImageResource(0)
            binding.cellTop.setImageResource(0)
            binding.cellTopRight.setImageResource(0)
            binding.cellMiddleLeft.setImageResource(0)
            binding.cellMiddle.setImageResource(0)
            binding.cellMiddleRight.setImageResource(0)
            binding.cellBottomLeft.setImageResource(0)
            binding.cellBottom.setImageResource(0)
            binding.cellBottomRight.setImageResource(0)
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

    private fun getXOImgResource(player: Player): Drawable {
        return when (player) {
            Player.PLAYER1 ->  ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
            Player.PLAYER2 ->  ContextCompat.getDrawable(this, R.drawable.ic_x_black)!!
            else ->  ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
        }
    }
}
