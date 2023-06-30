package org.android.go.sopt.feature.signup

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.base.ID_REGEX
import org.android.go.sopt.data.datasource.base.PW_REGEX
import org.android.go.sopt.data.datasource.model.RequestSignUpDto
import org.android.go.sopt.data.datasource.model.ResponseSignUpDto
import org.android.go.sopt.data.repository.SignUpRepository

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {
    private val _signUpResult: MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult

    private val idRegex = ID_REGEX.toRegex()
    private val pwRegex = PW_REGEX.toRegex()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val spec = MutableLiveData<String>()

    fun validID(id: String): Boolean {
        return id.matches(idRegex) && id.isNotBlank()
    }

    fun validPW(pw: String): Boolean {
        return pw.matches(pwRegex) && pw.isNotBlank()
    }

    fun isValid(): Boolean {
        return validID(id.value.orEmpty()) && validPW(pw.value.orEmpty()) && name.value.orEmpty()
            .isNotBlank() && spec.value.orEmpty().isNotBlank()
    }

    val isEnabledBtn = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = isValid() }
        addSource(pw) { value = isValid() }
        addSource(name) { value = isValid() }
        addSource(spec) { value = isValid() }
    }

    fun doSignUp(id: String, pw: String, name: String, spec: String) {
        viewModelScope.launch {
            signUpRepository.signUp(RequestSignUpDto(id, pw, name, spec)).onSuccess { response ->
                _signUpResult.value = response
            }.onFailure { error ->
                Log.d("SignUpView", "SignUp 실패")
            }
        }
    }
}
