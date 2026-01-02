package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import com.fit.exercises.presentation.controller.dto.GetAllExercisesResponse
import com.fit.utils.toExerciseDTO

class GetAllExercisesUseCase(
    private val exerciseRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(): GetAllExercisesResponse {
        val response = exerciseRepo.findAll().map { it.toExerciseDTO() }
        return GetAllExercisesResponse(response)
    }
}

