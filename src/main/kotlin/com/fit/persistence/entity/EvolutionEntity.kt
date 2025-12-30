package com.fit.persistence.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.model.naming.NamingStrategies
import java.math.BigDecimal
import java.time.Instant

@MappedEntity(value = "evolutions", namingStrategy = NamingStrategies.UnderScoreSeparatedLowerCase::class)
data class EvolutionEntity(
    @field:Id
    @field:GeneratedValue
    val id: Long? = null,

    val userId: Long,

    val overallScore: BigDecimal? = null,
    val bodyFatPercentage: BigDecimal? = null,

    val visualBodyComposition: String? = null,
    val torsoProportion: String? = null,
    val shoulderSymmetry: String? = null,
    val overallPosture: String? = null,

    @DateCreated
    val createdAt: Instant? = null
)