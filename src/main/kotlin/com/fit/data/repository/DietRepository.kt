package com.fit.data.repository

import com.fit.data.persistence.entity.DietEntity
import com.fit.data.persistence.entity.DietGoalEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank

@JdbcRepository(dialect = Dialect.MYSQL)
interface DietRepository : CoroutineCrudRepository<DietEntity, Long> {

    @Transactional
    suspend fun save(@NotBlank content: String, userId: Long, goal: DietGoalEntity): DietEntity

    @Transactional
    suspend fun findByIdAndUserId(id: Long, userId: Long): DietEntity

}