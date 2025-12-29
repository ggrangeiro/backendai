package com.fit.auth.infra.persistence

import com.fit.auth.domain.model.RefreshToken
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.infra.persistence.entity.RefreshTokenEntity
import com.fit.auth.infra.persistence.repository.RefreshTokenRepository
import jakarta.inject.Singleton
import java.time.Instant

@Singleton
class RefreshTokenRepositoryAdapter(
    private val repo: RefreshTokenRepository
) : RefreshTokenRepositoryPort {

    override suspend fun save(token: RefreshToken): RefreshToken {
        val entity = RefreshTokenEntity(
            id = if (token.id == 0L) null else token.id,
            userId = token.userId,
            tokenHash = token.tokenHash,
            expiresAt = token.expiresAt,
            revokedAt = token.revokedAt
        )
        val saved = repo.save(entity)
        return saved.toDomain()
    }

    override suspend fun findByTokenHash(tokenHash: String): RefreshToken? =
        repo.findByTokenHash(tokenHash)?.toDomain()


    override suspend fun revokeByTokenHash(tokenHash: String, revokedAt: Instant): Boolean {
        val entity = repo.findByTokenHash(tokenHash) ?: return false
        if (entity.revokedAt != null) return false

        repo.update(entity.copy(revokedAt = revokedAt))
        return true
    }

    private fun RefreshTokenEntity.toDomain(): RefreshToken =
        RefreshToken(
            id = this.id!!,
            userId = this.userId,
            tokenHash = this.tokenHash,
            expiresAt = this.expiresAt,
            revokedAt = this.revokedAt
        )
}
