package org.android.go.sopt

import androidx.lifecycle.*
import org.android.go.sopt.data.remote.ServicePool
import org.android.go.sopt.data.remote.model.RequestSignUpDto
import org.android.go.sopt.data.remote.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val _signUpResult : MutableLiveData<ResponseSignUpDto> = MutableLiveData()
    val signUpResult: LiveData<ResponseSignUpDto> = _signUpResult
    val idRegex = "\\w{6,10}".toRegex()
    val pwRegex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#\$%^+\\-=]).{6,10}$".toRegex()

   private val signUpService = ServicePool.signUpService

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val spec = MutableLiveData<String>()

    fun validID(id : String) : Boolean {
        return id.matches(idRegex) && id.isNotBlank()
    }

    fun validPW(pw : String) : Boolean {
        return pw.matches(pwRegex) && pw.isNotBlank()
    }


    fun isValid() : Boolean {
        return validID(id.value.orEmpty()) && validPW(pw.value.orEmpty()) && name.value.orEmpty().isNotBlank()  && spec.value.orEmpty().isNotBlank()
    }

    val isEnabledBtn = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = isValid() }
        addSource(pw) { value = isValid() }
        addSource(name) { value = isValid() }
        addSource(spec) { value = isValid() }
    }


    fun signUp(id: String, pw: String, name: String, spec: String) {
        signUpService.signUp(
            RequestSignUpDto(
                id, pw, name, spec
            )
        ).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    _signUpResult.value = response.body()
                } else {

                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}
