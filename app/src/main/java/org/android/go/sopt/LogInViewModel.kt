package org.android.go.sopt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.remote.ServicePool.logInService
import org.android.go.sopt.data.remote.model.RequestLogInDto
import org.android.go.sopt.data.remote.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInViewModel : ViewModel() {
    private val _logInResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    val logInResult: LiveData<ResponseLogInDto> = _logInResult

    fun logIn(id: String, pw: String) {
        logInService.logIn(
            RequestLogInDto(
                id,
                pw,
            ),
        ).enqueue(object : Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ) {
                if (response.isSuccessful) {
                    _logInResult.value = response.body()
                } else {
                }
            }

            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
