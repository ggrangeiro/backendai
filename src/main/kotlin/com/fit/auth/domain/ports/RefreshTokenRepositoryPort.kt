package com.fit.auth.domain.ports

import java.time.Instant

interface RefreshTokenRepositoryPort {
    suspend fun revokeByTokenHash(tokenHash: String, revokedAt: Instant): Boolean
}