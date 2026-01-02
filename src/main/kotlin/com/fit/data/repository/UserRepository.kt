package com.fit.data.repository

import com.fit.data.persistence.entity.UserEntity
import com.fit.data.persistence.entity.UserRole
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank

@JdbcRepository(dialect = Dialect.MYSQL)
interface UserRepository : CoroutineCrudRepository<UserEntity, Long> {

    @Transactional
    suspend fun save(
        @NotBlank email: String,
        @NotBlank password: String,
        @NotBlank name: String,
        role: UserRole
    ): UserEntity

    suspend fun findByEmail(email: String): UserEntity?

    suspend fun findByPersonalId(id: Long): List<UserEntity?>
}