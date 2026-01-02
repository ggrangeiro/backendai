package com.fit.data.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.Embeddable
import io.micronaut.data.annotation.EmbeddedId
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant

@Embeddable
data class UserExerciseId(
    @MappedProperty("user_id")
    val userId: Long,
    @MappedProperty("exercise_id")
    val exerciseId: Long
)


@MappedEntity(value = "user_exercises", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class UserExerciseEntity(
    @field:EmbeddedId
    val id: UserExerciseId,

    @DateCreated
    val createdAt: Instant? = null
)