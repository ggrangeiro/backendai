package com.fit.auth.domain.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class LogoutRequest(val refreshToken: String)

@Serdeable
data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serdeable
data class SignUpResponse(val email: String, val password: String)