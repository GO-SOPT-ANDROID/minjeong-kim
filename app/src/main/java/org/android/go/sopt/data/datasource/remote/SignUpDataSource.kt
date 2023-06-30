package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.datasource.model.RequestSignUpDto
import org.android.go.sopt.data.datasource.model.ResponseSignUpDto

class SignUpDataSource {

    private val signUpService = ServicePool.signUpService

    suspend fun getSignUpData(request: RequestSignUpDto): ResponseSignUpDto = signUpService.signUp(request)
}
