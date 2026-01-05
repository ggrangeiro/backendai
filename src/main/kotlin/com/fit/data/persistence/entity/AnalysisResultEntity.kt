package com.fit.data.persistence.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AnalysisResultEntity(
    val isValidContent: Boolean,
    val score: Int,
    val repetitions: Int? = 0,
    val gender: String? = null,
    val formCorrection: String? = null,
    val feedback: List<FeedbackItemEntity> = emptyList(),
    val strengths: List<String> = emptyList(),
    val improvements: List<ImprovementItemEntity> = emptyList(),
    val muscleGroups: List<String> = emptyList()
)