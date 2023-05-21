package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.data.remote.ServicePool.logInService
import org.android.go.sopt.data.remote.model.RequestLogInDto
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseLogInDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    //private var textId: String ?= null
    //private var textPw: String ?= null
    //private var textName: String ?= null
    //private var textSpec: String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btSi.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        clickLogInBtn()
    }

    // 배경 터치 시 키보드 내림
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    private fun clickLogInBtn() {
        binding.btLog.setOnClickListener(){
            completeLogIn()
        }
    }


    //intent = Intent(this@MainActivity, HomeActivity::class.java)
    //startActivity(intent)


    private fun completeLogIn() {
        logInService.logIn(
            with(binding) {
                RequestLogInDto(
                    etId.text.toString(),
                    etPw.text.toString(),
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        Snackbar.make(binding.root, "로그인에 성공했습니다.", Snackbar.LENGTH_SHORT).show() }

                    intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)

                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { Snackbar.make(binding.root, "서버통신 실패(40 x)", Snackbar.LENGTH_SHORT).show() }
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                // 왜 안 오노
                t.printStackTrace()
                Snackbar.make(binding.root, "서버통신 실패(응답값 x)", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}