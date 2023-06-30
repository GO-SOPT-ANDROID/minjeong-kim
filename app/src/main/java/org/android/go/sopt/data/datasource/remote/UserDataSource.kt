package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.datasource.model.ResponseUserDTO

class UserDataSource {

    private val userService = UserServicePool.userService

    suspend fun getUserData(): ResponseUserDTO = userService.getUser()
}
