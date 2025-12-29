package com.fit.auth.infra.security

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("auth.jwt")
data class JwtConfig(
    val issuer: String,
    val secret: String,
    val accessTokenMinutes: Long,
    val refreshTokenDays: Long
)