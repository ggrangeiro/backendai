package com.fit.persistence.entity

import io.micronaut.data.annotation.*
import io.micronaut.data.model.naming.NamingStrategies
import java.math.BigDecimal
import java.time.Instant

@MappedEntity(value = "users", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class UserEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,
    val name: String = "",
    val email: String,
    val password: String,
    val isActive: Boolean = true,
    val weightKg: BigDecimal? = null,
    val heightCm: BigDecimal? = null,
    val bodyFatPct: BigDecimal? = null,
    val targetBodyFatPct: BigDecimal? = null,
    val personalId: Long? = null,
    val sex: Sex? = null,
    val role: UserRole = UserRole.USER,
    @DateCreated
    val createdAt: Instant? = null,
    @DateUpdated
    val updatedAt: Instant? = null
)