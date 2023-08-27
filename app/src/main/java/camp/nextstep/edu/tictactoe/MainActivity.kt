package camp.nextstep.edu.tictactoe

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: TictactoeViewModel by viewModels()

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
        viewModel.clickBoard.observe(this) {
            // pair(position, turn)
            when(it.first) {
                1 -> {binding.cellTopLeft.setImageDrawable(getXOImgResource(it.second))}
                2 -> {binding.cellTop.setImageDrawable(getXOImgResource(it.second))}
                3 -> {binding.cellTopRight.setImageDrawable(getXOImgResource(it.second))}
                4 -> {binding.cellMiddleLeft.setImageDrawable(getXOImgResource(it.second))}
                5 -> {binding.cellMiddle.setImageDrawable(getXOImgResource(it.second))}
                6 -> {binding.cellMiddleRight.setImageDrawable(getXOImgResource(it.second))}
                7 -> {binding.cellBottomLeft.setImageDrawable(getXOImgResource(it.second))}
                8 -> {binding.cellBottom.setImageDrawable(getXOImgResource(it.second))}
                9 -> {binding.cellBottomRight.setImageDrawable(getXOImgResource(it.second))}
            }
        }

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
            R.id.menu_two ->
                Toast.makeText(this, "TODO: 2인 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_random ->
                Toast.makeText(this, "TODO: 랜덤 모드로 전환", Toast.LENGTH_SHORT).show()
            R.id.menu_draw ->
                Toast.makeText(this, "TODO: 무승부 모드로 전환", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun getXOImgResource(turn: Int): Drawable {
        return when (turn) {
            0 -> {
                ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
            }
            1 -> {
                ContextCompat.getDrawable(this, R.drawable.ic_x_black)!!
            }
            else -> {
                ContextCompat.getDrawable(this, R.drawable.ic_o_black)!!
            }
        }
    }
}
