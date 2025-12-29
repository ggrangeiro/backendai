package com.fit.auth.domain.ports

import com.fit.auth.domain.model.User
import java.time.Instant

data class TokenPair(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresAt: Instant,
    val refreshTokenExpiresAt: Instant
)

interface TokenProviderPort {
    fun generateTokens(user: User): TokenPair
    fun generateAccessToken(user: User): Pair<String, Instant>
}