package com.fit.analysis.domain.usecase

import com.fit.analysis.domain.ports.AnalysisRepositoryPort
import com.fit.analysis.presentation.controller.dto.CreateAnalysisRequest
import com.fit.analysis.presentation.controller.dto.CreateAnalysisResponse
import com.fit.data.persistence.entity.AnalysisEntity
import com.fit.utils.toDTO

class CreateAnalysisUseCase(
    private val analysisRepo: AnalysisRepositoryPort,
) {
    suspend fun execute(req: CreateAnalysisRequest): CreateAnalysisResponse {
        val a = AnalysisEntity(
            id = null,
            userId = req.analysis.userId,
            type = req.analysis.type,
            result = req.analysis.result
        )
        val response = analysisRepo.save(a).toDTO()
        return CreateAnalysisResponse(response)
    }
}


