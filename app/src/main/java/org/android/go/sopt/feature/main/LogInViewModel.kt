package org.android.go.sopt.feature.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.model.RequestLogInDto
import org.android.go.sopt.data.datasource.model.ResponseLogInDto
import org.android.go.sopt.data.repository.LogInRepository

class LogInViewModel(private val logInRepository: LogInRepository) : ViewModel() {
    private val _logInResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    val logInResult: LiveData<ResponseLogInDto> = _logInResult

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun doLogIn(id: String, pw: String) {
        viewModelScope.launch {
            logInRepository.logIn(RequestLogInDto(id, pw)).onSuccess { response ->
                _logInResult.value = response
            }.onFailure { error ->
                Log.d("LogInView", "LogIn 실패")
            }
        }
    }
}
