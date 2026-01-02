package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import com.fit.exercises.presentation.controller.dto.EditExerciseRequest
import com.fit.exercises.presentation.controller.dto.EditExerciseResponse
import com.fit.utils.toExerciseDTO
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class EditExerciseUseCase(
    private val exerciseRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(req: EditExerciseRequest): EditExerciseResponse {
        exerciseRepo.findByUserId(req.id) ?: throw HttpStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Exercise ${req.name} already exists"
        )

        val response = exerciseRepo.updateExercise(id = req.id, name = req.name).toExerciseDTO()
        return EditExerciseResponse(response)
    }
}

