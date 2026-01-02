package com.fit.diet.presentation.controller.dto

import com.fit.data.persistence.entity.DietGoalEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateDietRequest(val content: String, val userId: Long, val goal: DietGoalEntity)

@Serdeable
data class CreateDietResponse(val diet: DietDTO)

@Serdeable
data class DeleteDietRequest(val userId: Long, val id: Long)

@Serdeable
data class DeleteDietResponse(val success: Boolean)

@Serdeable
data class GetAllDietsResponse(val diets: List<DietDTO>)

@Serdeable
data class GetDietResponse(val diet: DietDTO)

@Serdeable
data class DietDTO(
    val id: Long,
    val content: String,
    val goal: DietGoalEntity
)