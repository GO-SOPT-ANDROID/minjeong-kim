package org.android.go.sopt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import org.android.go.sopt.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profile()
    }

    // 배경 터치 시 키보드 내림
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    // 받아온 이름, 특기 화면에 출력
    @SuppressLint("SetTextI18n")
    private fun profile() {
        with(binding) {
            val textId = intent.getStringExtra("NAME")
            val textSpec = intent.getStringExtra("SPEC")

            tvPfname.text = "이름: $textId"
            tvPfspec.text = "특기: $textSpec"
        }
    }
}