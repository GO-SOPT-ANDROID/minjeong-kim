package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
    }

    // 배경 터치 시 키보드 내리기
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    // 조건에 맞으면 값 넘겨줌
    private fun signUp() {
        with(binding) {
            btFsu.setOnClickListener {
                if ((etId.length() in 6..10 ) && (etPw.length() in 8..12)) {
                    intent.putExtra("ID", etId.text.toString())
                    intent.putExtra("PW", etPw.text.toString())
                    intent.putExtra("NAME", etName.text.toString())
                    intent.putExtra("SPEC", etSpec.text.toString())

                    Snackbar.make(
                        binding.root,
                        "회원가입이 완료되었습니다.",
                        Snackbar.LENGTH_SHORT
                    ).show()

                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}