package org.android.go.sopt.data.remote.service


import org.android.go.sopt.data.remote.model.ResponseUserDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {
    @GET("/api/users")
    suspend fun getUser(
        @Query("page") num: Int = 2
    ): ResponseUserDTO
}