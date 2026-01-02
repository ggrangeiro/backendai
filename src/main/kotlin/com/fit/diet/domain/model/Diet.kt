package com.fit.diet.domain.model

import com.fit.data.persistence.entity.DietGoalEntity

data class Diet(
    val id: Long,
    val content: String,
    val goal: DietGoalEntity,
)