package com.fit.auth.infra.persistence.repository

import com.fit.auth.infra.persistence.entity.RefreshTokenEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@JdbcRepository(dialect = Dialect.H2)
interface RefreshTokenRepository : CoroutineCrudRepository<RefreshTokenEntity, Long> {

    @Transactional
    suspend fun save(
        @NotBlank userId: Long,
        @NotBlank tokenHash: String,
        @NotBlank expiresAt: Instant,
    ): RefreshTokenEntity

    suspend fun findByUserId(userId: Long): RefreshTokenEntity?

    suspend fun findByTokenHash(tokenHash: String): RefreshTokenEntity?

}