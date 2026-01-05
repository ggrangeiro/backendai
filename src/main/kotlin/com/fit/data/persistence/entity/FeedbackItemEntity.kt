package com.fit.data.persistence.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class FeedbackItemEntity(
    val message: String,
    val score: Int
)