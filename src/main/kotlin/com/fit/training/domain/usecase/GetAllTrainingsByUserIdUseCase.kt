package com.fit.training.domain.usecase

import com.fit.training.domain.ports.TrainingRepositoryPort
import com.fit.training.presentation.controller.dto.GetAllTrainingsResponse
import com.fit.utils.toTrainingDTO

class GetAllTrainingsByUserIdUseCase(
    private val trainingRepo: TrainingRepositoryPort
) {
    suspend fun execute(id: Long): GetAllTrainingsResponse {
        val training = trainingRepo.findAllByUserId(id)

        val response = training.map { it.toTrainingDTO() }

        return GetAllTrainingsResponse(response)
    }
}