package com.fit.training.domain.usecase

import com.fit.training.domain.ports.TrainingRepositoryPort
import com.fit.training.presentation.controller.dto.GetTrainingResponse
import com.fit.utils.toTrainingDTO

class GetTrainingIdByUserIdUseCase(
    private val trainingRepo: TrainingRepositoryPort
) {
    suspend fun execute(userId: Long, trainingId: Long): GetTrainingResponse {
        val response = trainingRepo.findUserIdAndTrainingId(userId = userId, trainingId = trainingId)
        return GetTrainingResponse(response.toTrainingDTO())
    }
}