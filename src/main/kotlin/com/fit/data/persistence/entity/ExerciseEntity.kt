package com.fit.data.persistence.entity

import io.micronaut.data.annotation.*
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant

@MappedEntity(value = "exercises", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class ExerciseEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,
    val name: String,
    @DateCreated
    val createdAt: Instant? = null,
    @DateUpdated
    val updatedAt: Instant? = null
)