package com.fit.training.domain.model

import com.fit.data.persistence.entity.TrainingGoalEntity

data class Training(
    val id: Long,
    val content: String,
    val goal: TrainingGoalEntity,
)