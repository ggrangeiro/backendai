package com.fit.analysis.domain.usecase

import com.fit.analysis.domain.ports.AnalysisRepositoryPort
import com.fit.analysis.presentation.controller.dto.DeleteAnalysisRequest
import com.fit.analysis.presentation.controller.dto.DeleteAnalysisResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class DeleteAnalysisUseCase(
    private val analysisRepo: AnalysisRepositoryPort,
) {
    suspend fun execute(req: DeleteAnalysisRequest): DeleteAnalysisResponse {

        try {
            analysisRepo.delete(userId = req.userId, analysisId = req.id)
            return DeleteAnalysisResponse(true)
        } catch (_: Exception) {
            throw HttpStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Analysis can not be deleted"
            )
        }
    }
}
