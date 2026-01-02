package com.fit.data.repository

import com.fit.data.persistence.entity.UserExerciseEntity
import com.fit.data.persistence.entity.UserExerciseId
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.time.Instant

@JdbcRepository(dialect = Dialect.MYSQL)
interface UserExerciseRepository : CoroutineCrudRepository<UserExerciseEntity, UserExerciseId> {

    @Query(
        """
        SELECT e.name as exercise_name, ue.created_at as assigned_at
        FROM user_exercises ue
        JOIN exercises e ON ue.exercise_id = e.id
        WHERE ue.user_id = :userId
    """
    )
    suspend fun findByIdUserId(userId: Long): List<UserExerciseDto>
}

@Introspected
data class UserExerciseDto(
    val exerciseName: String,
    val assignedAt: Instant?
)