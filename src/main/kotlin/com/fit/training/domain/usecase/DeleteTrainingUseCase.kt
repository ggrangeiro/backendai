package com.fit.training.domain.usecase

import com.fit.training.domain.ports.TrainingRepositoryPort
import com.fit.training.presentation.controller.dto.DeleteTrainingRequest
import com.fit.training.presentation.controller.dto.DeleteTrainingResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class DeleteTrainingUseCase(
    private val trainingRepo: TrainingRepositoryPort,
) {
    suspend fun execute(req: DeleteTrainingRequest): DeleteTrainingResponse {

        try {
            trainingRepo.delete(userId = req.userId, trainingId = req.id)
            return DeleteTrainingResponse(true)
        } catch (_: Exception) {
            throw HttpStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Exercise can not be deleted"
            )
        }
    }
}
