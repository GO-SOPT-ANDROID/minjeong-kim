package org.android.go.sopt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.UserServicePool
import org.android.go.sopt.data.remote.model.ResponseUserDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _userResult : MutableLiveData<ResponseUserDTO> = MutableLiveData()
    val userResult : LiveData<ResponseUserDTO> = _userResult

    private val userService = UserServicePool.userService

    fun makeList() {
        userService.getUser( ).enqueue(object : Callback<ResponseUserDTO> {
            override fun onResponse(
                call: Call<ResponseUserDTO>,
                response: Response<ResponseUserDTO>,
            ) {
                if (response.isSuccessful) {
                    _userResult.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: Call<ResponseUserDTO>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}