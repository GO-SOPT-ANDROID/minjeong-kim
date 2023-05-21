package org.android.go.sopt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        clickSignUpBtn()
    }

    // 배경 터치 시 키보드 내리기
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }

    private fun clickSignUpBtn() {
        binding.btFsu.setOnClickListener() {
            if (canSignUp()) {
                completeSignUp()
            }
        }
    }

    // 조건에 맞으면 값 넘겨줌
    private fun canSignUp() :Boolean {
        return binding.etId.text.length in 6..10
                && binding.etPw.text.length in 8..12
                && binding.etName.text.isNotBlank()
                && binding.etSpec.text.isNotBlank()

    }



    private val signUpService = ServicePool.signUpService

    private fun completeSignUp() {
        signUpService.signUp(
            with(binding) {
                RequestSignUpDto(
                    etId.text.toString(),
                    etPw.text.toString(),
                    etName.text.toString(),
                    etSpec.text.toString()
                )
            }
        ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    response.body()?.message?.let {
                        Snackbar.make(binding.root, "회원가입에 성공했습니다.", Snackbar.LENGTH_SHORT).show() }

                    if (!isFinishing) finish()
                } else {
                    // 실패한 응답
                    response.body()?.message?.let { Snackbar.make(binding.root, "서버통신 실패(40 x)", Snackbar.LENGTH_SHORT).show() }
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                // 왜 안 오노
                //t.message?.let { Snackbar.make(binding.root, "서버통신 실패(응답값 x)", Snackbar.LENGTH_SHORT).show() }
                t.printStackTrace()
                Snackbar.make(binding.root, "서버통신 실패(응답값 x)", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}