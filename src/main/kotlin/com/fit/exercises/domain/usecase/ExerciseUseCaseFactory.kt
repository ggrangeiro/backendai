package com.fit.exercises.domain.usecase

import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import jakarta.inject.Singleton

@Singleton
class ExerciseUseCaseFactory(
    val exerciseRepo: ExerciseRepositoryPort
) {
    fun getAllExercisesUseCase() = GetAllExercisesUseCase(exerciseRepo)
    fun createExerciseUseCase() = CreateExerciseUseCase(exerciseRepo)
    fun getExercisesByUserIdUseCase() = GetExercisesByUserIdUseCase(exerciseRepo)
    fun editExerciseByIdUseCase() = EditExerciseUseCase(exerciseRepo)
    fun assignExerciseUseCase() = AssignExerciseUseCase(exerciseRepo)
}