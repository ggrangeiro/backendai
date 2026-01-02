package com.fit.training.presentation.controller.dto

import com.fit.data.persistence.entity.TrainingGoalEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateTrainingRequest(val content: String, val userId: Long, val goal: TrainingGoalEntity)

@Serdeable
data class CreateTrainingResponse(val training: TrainingDTO)

@Serdeable
data class DeleteTrainingRequest(val userId: Long, val id: Long)

@Serdeable
data class DeleteTrainingResponse(val success: Boolean)

@Serdeable
data class GetAllTrainingsResponse(val trainings: List<TrainingDTO>)

@Serdeable
data class GetTrainingResponse(val training: TrainingDTO)

@Serdeable
data class TrainingDTO(
    val id: Long,
    val content: String,
    val goal: TrainingGoalEntity
)