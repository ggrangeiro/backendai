package com.fit.data.persistence.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ImprovementItemEntity(
    val instruction: String,
    val detail: String
)