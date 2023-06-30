package org.android.go.sopt.data.datasource.service

import org.android.go.sopt.data.datasource.model.RequestSignUpDto
import org.android.go.sopt.data.datasource.model.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): ResponseSignUpDto
}