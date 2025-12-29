package com.fit.auth.domain.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String = "Bearer",
    val expiresInSeconds: Long
)
@Serdeable
data class RefreshResponse(
    val accessToken: String,
    val tokenType: String = "Bearer",
    val expiresInSeconds: Long
)
@Serdeable
data class LoginRequest(val email: String, val password: String)
@Serdeable
data class LogoutRequest(val refreshToken: String)
@Serdeable
data class RefreshRequest(val refreshToken: String)