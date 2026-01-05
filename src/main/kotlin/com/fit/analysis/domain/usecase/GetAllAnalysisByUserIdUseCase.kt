package com.fit.analysis.domain.usecase

import com.fit.analysis.domain.ports.AnalysisRepositoryPort
import com.fit.analysis.presentation.controller.dto.GetAllAnalysisResponse
import com.fit.utils.toDTO

class GetAllAnalysisByUserIdUseCase(
    private val analysisRepo: AnalysisRepositoryPort
) {
    suspend fun execute(id: Long): GetAllAnalysisResponse {
        val analysis = analysisRepo.findAllByUserId(id)
        val response = analysis.map { it.toDTO() }
        return GetAllAnalysisResponse(response)
    }
}