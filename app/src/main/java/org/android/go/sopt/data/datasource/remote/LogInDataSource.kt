package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.datasource.model.RequestLogInDto
import org.android.go.sopt.data.datasource.model.ResponseLogInDto

class LogInDataSource {

    private val logInService = ServicePool.logInService

    suspend fun getLogInData(request: RequestLogInDto): ResponseLogInDto = logInService.logIn(request)
}
