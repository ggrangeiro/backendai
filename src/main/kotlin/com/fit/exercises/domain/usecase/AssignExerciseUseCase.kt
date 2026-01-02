package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import com.fit.exercises.presentation.controller.dto.AssignExerciseRequest
import com.fit.exercises.presentation.controller.dto.AssignExerciseResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class AssignExerciseUseCase(
    private val exerciseRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(req: AssignExerciseRequest): AssignExerciseResponse {
        val response = exerciseRepo.assignExerciseToUser(exerciseId = req.exerciseId, userId = req.userId)
        if (response) {
            val exercises = exerciseRepo.findByUserId(req.userId)
            return AssignExerciseResponse(exercises.map { it.name })
        } else {
            throw HttpStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Exercise can not be assigned to user ${req.userId}"
            )
        }
    }
}
