package org.android.go.sopt.data.datasource.service


import org.android.go.sopt.data.datasource.model.RequestLogInDto
import org.android.go.sopt.data.datasource.model.ResponseLogInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {
    @POST("sign-in")
    fun logIn(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>
}