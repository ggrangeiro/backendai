package com.fit.data.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant

@MappedEntity(value = "refresh_tokens", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class RefreshTokenEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,

    val userId: Long,
    val tokenHash: String,
    val expiresAt: Instant,
    val revokedAt: Instant? = null,

    @DateCreated
    val createdAt: Instant? = null
)