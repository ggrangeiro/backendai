package com.fit.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Embeddable
import io.micronaut.data.annotation.EmbeddedId
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant

@Embeddable
data class UserExerciseId(
    val userId: Long,
    val exerciseId: Long
)


@MappedEntity(value = "user_exercises", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class UserExerciseEntity(
    @field:EmbeddedId
    val id: UserExerciseId,

    @DateCreated
    val createdAt: Instant? = null
)