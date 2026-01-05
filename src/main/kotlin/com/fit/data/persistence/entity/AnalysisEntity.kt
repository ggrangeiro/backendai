package com.fit.data.persistence.entity

import io.micronaut.data.annotation.*
import io.micronaut.data.model.DataType
import io.micronaut.data.model.naming.NamingStrategies
import java.time.Instant
import javax.annotation.processing.Generated

@MappedEntity(value = "analysis", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class AnalysisEntity(
    @field:Id
    @GeneratedValue
    val id: Long?,
    val userId: Long,
    val type: AnalysisType,
    @DateCreated
    @MappedProperty("created_at")
    val createdAt: Instant? = null,
    @MappedProperty("result_data")
    @TypeDef(type = DataType.JSON)
    val result: AnalysisResultEntity,
)


enum class AnalysisType {
    BODY_COMPOSITION, EXECUTION, POSTURE
}