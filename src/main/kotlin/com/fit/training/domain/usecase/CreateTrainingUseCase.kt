package com.fit.training.domain.usecase

import com.fit.training.domain.ports.TrainingRepositoryPort
import com.fit.training.presentation.controller.dto.CreateTrainingRequest
import com.fit.training.presentation.controller.dto.CreateTrainingResponse
import com.fit.utils.toTrainingDTO

class CreateTrainingUseCase(
    private val trainingRepo: TrainingRepositoryPort,
) {
    suspend fun execute(req: CreateTrainingRequest): CreateTrainingResponse {
        val response = trainingRepo.save(content = req.content, req.userId, req.goal).toTrainingDTO()
        return CreateTrainingResponse(response)
    }
}
