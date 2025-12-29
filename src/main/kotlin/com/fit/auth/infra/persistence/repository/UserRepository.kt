package com.fit.auth.infra.persistence.repository

import com.fit.auth.infra.persistence.entity.UserEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import io.micronaut.transaction.annotation.Transactional
import jakarta.validation.constraints.NotBlank

@JdbcRepository(dialect = Dialect.H2)
interface UserRepository : CoroutineCrudRepository<UserEntity, Long> {

    @Transactional
    suspend fun save(
        @NotBlank email: String,
        @NotBlank password: String,
        @NotBlank name: String
    ): UserEntity

    suspend fun findByEmail(email: String): UserEntity?
}