package com.fit.auth.domain.model

import java.time.Instant


data class RefreshToken(
    val id: Long,
    val userId: Long,
    val tokenHash: String,
    val expiresAt: Instant,
    val revokedAt: Instant?
) {
    fun isRevoked(): Boolean = revokedAt != null
    fun isExpired(now: Instant): Boolean = expiresAt.isBefore(now) || expiresAt == now
}