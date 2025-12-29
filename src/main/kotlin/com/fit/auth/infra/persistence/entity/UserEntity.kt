package com.fit.auth.infra.persistence.entity

import io.micronaut.data.annotation.*
import io.micronaut.data.model.naming.NamingStrategies
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import java.time.Instant

@MappedEntity(value = "users", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class UserEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,

    @field:Email
    @field:NotEmpty
    val email: String,

    @field:NotEmpty
    val name: String,


    val password: String,
    val isActive: Boolean = true,
    @DateCreated
    val createdAt: Instant? = null,

    @DateUpdated
    val updatedAt: Instant? = null
)