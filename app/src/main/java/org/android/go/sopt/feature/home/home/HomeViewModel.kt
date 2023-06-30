package org.android.go.sopt.feature.home.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.model.ResponseUserDTO
import org.android.go.sopt.data.repository.UserRepository

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _userResult: MutableLiveData<ResponseUserDTO> = MutableLiveData()
    val userResult: LiveData<ResponseUserDTO> = _userResult

    fun makeList() {
        viewModelScope.launch {
            userRepository.getUser().onSuccess {
                    response ->
                _userResult.value = response
            }.onFailure {
                    error ->
                Log.d("HomeView", "makeUserList 실패")
            }
        }
    }
}
