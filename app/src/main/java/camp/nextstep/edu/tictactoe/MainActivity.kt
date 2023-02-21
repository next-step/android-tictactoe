package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var imageViewArray: Array<Array<ImageView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = mainViewModel
        setContentView(binding.root)
        addObserver()
        addImageViewList()
    }

    private fun addObserver() {
        mainViewModel.gameStatus.observe(this) { gameStatus ->
            when (gameStatus) {
                TicTacToeStatus.DRAW -> showToastMessage(getString(R.string.draw))
                TicTacToeStatus.O_WIN -> showToastMessage(getString(R.string.winner, OX.O.name))
                TicTacToeStatus.X_WIN -> showToastMessage(getString(R.string.winner, OX.X.name))
                else -> Unit
            }
        }

        mainViewModel.cellsLiveData.observe(this) { cells ->
            cells.forEachIndexed { xIndex, oxen ->
                oxen.forEachIndexed { yIndex, ox ->
                    setImageResource(xIndex, yIndex, ox)
                }
            }
        }
    }

    private fun addImageViewList() {
        imageViewArray = arrayOf(
            arrayOf(binding.cellTopLeft, binding.cellTop, binding.cellTopRight),
            arrayOf(binding.cellMiddleLeft, binding.cellMiddle, binding.cellMiddleRight),
            arrayOf(binding.cellBottomLeft, binding.cellBottom, binding.cellBottomRight),
        )
    }

    private fun setImageResource(x: Int, y: Int, ox: OX?) {
        val drawableId = when (ox) {
            OX.X -> R.drawable.ic_x_black
            OX.O -> R.drawable.ic_o_black
            else -> 0
        }

        imageViewArray[x][y].setImageResource(drawableId)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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
