package com.fit.training.domain.usecase

import com.fit.training.domain.ports.TrainingRepositoryPort
import jakarta.inject.Singleton

@Singleton
class TrainingUseCaseFactory(
    val trainingRepo: TrainingRepositoryPort
) {
    fun createTrainingUseCase() = CreateTrainingUseCase(trainingRepo)
    fun getTrainingByUserIdUseCase() = GetTrainingIdByUserIdUseCase(trainingRepo)
    fun getAllTrainingsByUserIdUseCase() = GetAllTrainingsByUserIdUseCase(trainingRepo)
    fun deleteTrainingByIdUseCase() = DeleteTrainingUseCase(trainingRepo)
}