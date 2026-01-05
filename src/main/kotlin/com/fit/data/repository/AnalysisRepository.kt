package com.fit.data.repository

import com.fit.data.persistence.entity.AnalysisEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.PageableRepository

@JdbcRepository(dialect = Dialect.MYSQL)
interface AnalysisRepository : PageableRepository<AnalysisEntity, Long> {
    fun findByUserId(userId: Long): List<AnalysisEntity>
}