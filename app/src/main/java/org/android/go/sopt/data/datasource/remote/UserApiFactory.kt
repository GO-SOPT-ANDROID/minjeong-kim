package org.android.go.sopt.data.datasource.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.datasource.service.UserService
import retrofit2.Retrofit

object UserApiFactory {

    val retrofitForUser: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    inline fun <reified T> createUserService(): T = retrofitForUser.create<T>(T::class.java)
}

object UserServicePool {
    val userService = UserApiFactory.createUserService<UserService>()
}