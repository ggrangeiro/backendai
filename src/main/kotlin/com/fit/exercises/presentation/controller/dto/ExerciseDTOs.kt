package com.fit.exercises.presentation.controller.dto

import com.fit.data.persistence.entity.ExerciseCategoryEntity
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateExerciseRequest(val name: String, val category: ExerciseCategoryEntity)

@Serdeable
data class CreateExerciseResponse(val exercise: ExerciseDTO)

@Serdeable
data class EditExerciseRequest(val name: String, val id: Long, val category: ExerciseCategoryEntity)

@Serdeable
data class EditExerciseResponse(val exercise: ExerciseDTO)

@Serdeable
data class GetExerciseByUserResponse(val exercises: List<ExerciseDTO>)

@Serdeable
data class GetAllExercisesResponse(val exercises: List<ExerciseDTO>)

@Serdeable
data class ExerciseDTO(
    val id: Long,
    val name: String
)

@Serdeable
data class AssignExerciseRequest(val exerciseId: Long, val userId: Long)

@Serdeable
data class AssignExerciseResponse(val assignedExercises: List<String>)