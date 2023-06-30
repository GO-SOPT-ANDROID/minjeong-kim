package org.android.go.sopt.data.repository

import android.util.Log
import org.android.go.sopt.data.datasource.model.ResponseUserDTO
import org.android.go.sopt.data.datasource.remote.UserDataSource

class UserRepository(private val userDataSource: UserDataSource) {

    suspend fun getUser(): Result<ResponseUserDTO> =
        runCatching {
            userDataSource.getUserData()
        }.onSuccess {
            Log.d("userRepository", "getUser 성공")
        }.onFailure {
            Log.d("userRepository", "getUser 실패")
        }
}
