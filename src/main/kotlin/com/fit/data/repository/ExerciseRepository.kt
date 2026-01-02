package com.fit.data.repository

import com.fit.data.persistence.entity.ExerciseEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank

@JdbcRepository(dialect = Dialect.MYSQL)
interface ExerciseRepository : CoroutineCrudRepository<ExerciseEntity, Long> {
    @Transactional
    suspend fun save(@NotBlank name: String): ExerciseEntity

    @Transactional
    suspend fun save(@NotBlank name: String, id: Long): ExerciseEntity

    suspend fun findByName(name: String): ExerciseEntity?
}