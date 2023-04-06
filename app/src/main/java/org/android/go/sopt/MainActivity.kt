package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var textId: String ?= null
    private var textPw: String ?= null
    private var textName: String ?= null
    private var textSpec: String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btSi.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

        signUp()
        login()
    }

    // 배경 터치 시 키보드 내림
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    // SignUpActivity에서 돌아올 때 결과 값을 받음
    private fun signUp() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                textId = result.data?.getStringExtra("ID")
                textPw = result.data?.getStringExtra("PW")
                textName = result.data?.getStringExtra("NAME")
                textSpec = result.data?.getStringExtra("SPEC")
            }
        }
    }

    // 저장된 id,pw와 비교
    private fun login() {
        with(binding) {
            btLog.setOnClickListener {
                if ((textId.equals(etId.text.toString())) && (textPw.equals(etPw.text.toString()))) {
                    Toast.makeText(
                        this@MainActivity,
                        "로그인에 성공했습니다",
                        Toast.LENGTH_SHORT
                    ).show()

                    intent = Intent(this@MainActivity, ProfileActivity::class.java)
                    intent.putExtra("NAME", textName)
                    intent.putExtra("SPEC", textSpec)

                    setResult(RESULT_OK, intent)
                    startActivity(intent)
                }
            }
        }
    }
}