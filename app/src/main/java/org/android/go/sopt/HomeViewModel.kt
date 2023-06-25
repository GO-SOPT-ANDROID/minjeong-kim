package org.android.go.sopt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.remote.UserServicePool
import org.android.go.sopt.data.remote.model.ResponseUserDTO

class HomeViewModel : ViewModel() {
    private val _userResult: MutableLiveData<ResponseUserDTO> = MutableLiveData()
    val userResult: LiveData<ResponseUserDTO> = _userResult

    private val userService = UserServicePool.userService

    fun makeList() {
        viewModelScope.launch {
            kotlin.runCatching {
                userService.getUser()
            }.fold(onSuccess = {
                    response ->
                _userResult.value = response
            }, onFailure = { error ->
                error.printStackTrace()
            })
        }
    }
}
