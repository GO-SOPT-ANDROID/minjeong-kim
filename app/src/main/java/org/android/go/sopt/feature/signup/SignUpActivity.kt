package org.android.go.sopt.feature.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import org.android.go.sopt.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        binding.textlayoutId.editText?.addTextChangedListener(idListener)
        binding.textlayoutPw.editText?.addTextChangedListener(pwListener)


        binding.btFsu.isEnabled = false


        canClickBtn()
        clickSignUpBtn()

    }

    private val idListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    !viewModel.validID(s.toString()) -> {
                        binding.textlayoutId.error = "아이디는 영문, 숫자 포함 6~10글자"
                    }
                    else -> {
                        binding.textlayoutId.error = null
                    }
                }
            }
        }
    }

    private val pwListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s != null) {
                when {
                    !viewModel.validPW(s.toString()) -> {
                        binding.textlayoutPw.error = "비밀번호는 영문, 숫자, 특수기호 포함 6~12글자"
                    }
                    else -> {
                        binding.textlayoutPw.error = null
                    }
                }
            }
        }
    }


    private fun canClickBtn() {
        viewModel.isEnabledBtn.observe(this@SignUpActivity) { isEnabledBtn ->
            binding.btFsu.isEnabled = viewModel.isValid()
        }
    }


    private fun clickSignUpBtn() {
        binding.btFsu.setOnClickListener{
            viewModel.signUp(
                binding.etId.text.toString(),
                binding.etPw.text.toString(),
                binding.etName.text.toString(),
                binding.etSpec.text.toString()
            )
        }
        viewModel.signUpResult.observe(this) { signUpResult ->
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