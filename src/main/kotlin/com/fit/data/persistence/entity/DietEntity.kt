package com.fit.data.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant


@MappedEntity(value = "diet", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class DietEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,
    val userId: Long,
    val content: String,
    val goal: DietGoalEntity,
    @DateCreated
    val createdAt: Instant? = null
)