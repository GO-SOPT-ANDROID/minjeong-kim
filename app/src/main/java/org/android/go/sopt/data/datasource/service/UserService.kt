package org.android.go.sopt.data.datasource.service


import org.android.go.sopt.data.datasource.model.ResponseUserDTO
import retrofit2.http.GET
import retrofit2.http.Query


interface UserService {
    @GET("/api/users")
    suspend fun getUser(
        @Query("page") num: Int = 2
    ): ResponseUserDTO
}