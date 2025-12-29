package com.fit.auth.domain.ports

import com.fit.auth.domain.model.RefreshToken
import java.time.Instant

interface RefreshTokenRepositoryPort {
    suspend fun save(token: RefreshToken): RefreshToken
    suspend fun findByTokenHash(tokenHash: String): RefreshToken?
    suspend fun revokeByTokenHash(tokenHash: String, revokedAt: Instant): Boolean
}