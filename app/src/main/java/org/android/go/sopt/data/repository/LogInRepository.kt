package org.android.go.sopt.data.repository

import android.util.Log
import org.android.go.sopt.data.datasource.model.RequestLogInDto
import org.android.go.sopt.data.datasource.model.ResponseLogInDto
import org.android.go.sopt.data.datasource.remote.LogInDataSource

class LogInRepository(private val logInDataSource: LogInDataSource) {

    suspend fun logIn(request: RequestLogInDto): Result<ResponseLogInDto> =
        runCatching {
            logInDataSource.getLogInData(request)
        }.onSuccess {
            Log.d("logInRepository", "logIn 성공")
        }.onFailure {
            Log.d("logInRepository", "logIn 실패")
        }
}
