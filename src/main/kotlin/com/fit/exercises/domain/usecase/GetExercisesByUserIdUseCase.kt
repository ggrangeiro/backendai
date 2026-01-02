@file:JvmName("GetAllExercisesUseCaseKt")

package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import com.fit.exercises.presentation.controller.dto.GetExerciseByUserResponse
import com.fit.utils.toExerciseDTO

class GetExercisesByUserIdUseCase(
    private val exercisesRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(id: Long): GetExerciseByUserResponse {
        val exercise = exercisesRepo.findByUserId(id)

        val response = exercise.map { it.toExerciseDTO() }

        return GetExerciseByUserResponse(response)
    }
}