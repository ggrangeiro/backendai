package com.fit.auth.infra.security

import com.fit.auth.domain.model.User
import com.fit.auth.domain.ports.TokenPair
import com.fit.auth.domain.ports.TokenProviderPort
import jakarta.inject.Singleton
import java.security.SecureRandom
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Singleton
class JjwtTokenProvider(
    private val cfg: JwtConfig
) : TokenProviderPort {

    private val random = SecureRandom()

    override fun generateTokens(user: User): TokenPair {
        val (access, accessExp) = generateAccessToken(user)
        val refresh = generateRefreshToken()
        val refreshExp = Instant.now().plus(cfg.refreshTokenDays, ChronoUnit.DAYS)

        return TokenPair(
            accessToken = access,
            refreshToken = refresh,
            accessTokenExpiresAt = accessExp,
            refreshTokenExpiresAt = refreshExp
        )
    }

    override fun generateAccessToken(user: User): Pair<String, Instant> {
        val now = Instant.now()
        val exp = now.plus(cfg.accessTokenMinutes, ChronoUnit.MINUTES)
        val jwt = "adsa"

        return (jwt to exp)
    }

    private fun generateRefreshToken(): String {
        val bytes = ByteArray(64)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }
}