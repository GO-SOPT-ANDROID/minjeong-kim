package org.android.go.sopt.feature.signup

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivitySignUpBinding
import org.android.go.sopt.util.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private val viewModel: SignUpViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        idListener()
        pwListener()

        binding.btFsu.isEnabled = false

        canClickBtn()
        clickSignUpBtn()
    }

    private fun idListener() {
        viewModel.id.observe(this) { id ->
            viewModel.let { viewModel ->
                if (!viewModel.validID(id)) {
                    binding.textlayoutId.error = "아이디는 영문, 숫자 포함 6~10글자"
                } else {
                    binding.textlayoutId.error = null
                }
            }
        }
    }

    private fun pwListener() {
        viewModel.pw.observe(this) { pw ->
            viewModel.let { viewModel ->
                if (!viewModel.validPW(pw)) {
                    binding.textlayoutPw.error = "비밀번호는 영문, 숫자, 특수기호 포함 6~12글자"
                } else {
                    binding.textlayoutPw.error = null
                }
            }
        }
    }

    private fun canClickBtn() {
        viewModel.isEnabledBtn.observe(this@SignUpActivity) {
            binding.btFsu.isEnabled = viewModel.isValid()
        }
    }

    private fun clickSignUpBtn() {
        binding.btFsu.setOnClickListener {
            viewModel.doSignUp(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString(),
                binding.etSpec.text.toString(),
            )
        }
        viewModel.signUpResult.observe(this) {
            finish()
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val imm: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        return super.dispatchTouchEvent(ev)
    }
}
