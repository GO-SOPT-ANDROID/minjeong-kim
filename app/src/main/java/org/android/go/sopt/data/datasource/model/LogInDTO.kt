package org.android.go.sopt.data.datasource.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestLogInDto(
    @SerialName("id")
    val id: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class ResponseLogInDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: SignUpData,
) {
    @Serializable
    data class SignUpData(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("skill")
        val skill: String,
    )
}