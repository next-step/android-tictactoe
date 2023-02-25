package camp.nextstep.edu.tictactoe

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import camp.nextstep.edu.tictactoe.databinding.ActivityMainBinding
import camp.nextstep.edu.tictactoe.domain.Point
import camp.nextstep.edu.tictactoe.model.MessageState
import camp.nextstep.edu.tictactoe.model.MessageState.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            vm = viewModel
            point = Point(0, 0)
            lifecycleOwner = this@MainActivity
        }
        observerViewMode()
    }

    private fun observerViewMode() {
        viewModel.showToast.observe(this) {
            showErrorMessage(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun showErrorMessage(messageState: MessageState) {
        when (messageState) {
            FINISH -> Toast.makeText(this, "게임이 종료되었습니다.", Toast.LENGTH_SHORT).show()
            X_WIN -> Toast.makeText(this, "X가 이겼습니다", Toast.LENGTH_SHORT).show()
            O_WIN -> Toast.makeText(this, "O가 이겼습니다", Toast.LENGTH_SHORT).show()
            IN_A_TIE -> Toast.makeText(this, "무승부 입니다", Toast.LENGTH_SHORT).show()
            ERROR -> Toast.makeText(this, "다른 곳을 선택해 주세요", Toast.LENGTH_SHORT).show()
        }
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
