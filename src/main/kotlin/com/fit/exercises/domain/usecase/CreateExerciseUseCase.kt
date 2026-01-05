package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import com.fit.exercises.presentation.controller.dto.CreateExerciseRequest
import com.fit.exercises.presentation.controller.dto.CreateExerciseResponse
import com.fit.utils.toExerciseDTO
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class CreateExerciseUseCase(
    private val exerciseRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(req: CreateExerciseRequest): CreateExerciseResponse {
        exerciseRepo.findByName(req.name)?.let {
            throw HttpStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Exercise ${req.name} already exists"
            )
        }

        val response = exerciseRepo.saveExercise(req.name, req.category).toExerciseDTO()

        return CreateExerciseResponse(response)
    }
}
