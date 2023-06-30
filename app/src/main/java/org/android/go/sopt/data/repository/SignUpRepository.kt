package org.android.go.sopt.data.repository

import android.util.Log
import org.android.go.sopt.data.datasource.model.RequestSignUpDto
import org.android.go.sopt.data.datasource.model.ResponseSignUpDto
import org.android.go.sopt.data.datasource.remote.SignUpDataSource

class SignUpRepository(private val signUpDataSource: SignUpDataSource) {

    suspend fun signUp(request: RequestSignUpDto): Result<ResponseSignUpDto> =
        runCatching {
            signUpDataSource.getSignUpData(request)
        }.onSuccess {
            Log.d("signUpRepository", "signUp 성공")
        }.onFailure {
            Log.d("signUpRepository", "signUp 실패")
        }
}
