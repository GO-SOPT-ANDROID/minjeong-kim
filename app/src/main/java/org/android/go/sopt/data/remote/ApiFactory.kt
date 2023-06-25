package org.android.go.sopt.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.android.go.sopt.BuildConfig
import org.android.go.sopt.data.remote.service.ImageService
import org.android.go.sopt.data.remote.service.LogInService
import org.android.go.sopt.data.remote.service.SignUpService
import retrofit2.Retrofit

object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder().addInterceptor(AuthInterceptor()).addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }).build()
    }

    val retrofitForAuth: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(client).build()
    }

    inline fun <reified T> createAuthService(): T = retrofitForAuth.create<T>(T::class.java)


}


object ServicePool {
    val signUpService = ApiFactory.createAuthService<SignUpService>()
    val logInService = ApiFactory.createAuthService<LogInService>()
    val imageService = ApiFactory.createAuthService<ImageService>()
}