package com.fit.analysis.domain.usecase

import com.fit.analysis.domain.ports.AnalysisRepositoryPort
import jakarta.inject.Singleton

@Singleton
class AnalysisUseCaseFactory(
    val analysisRepo: AnalysisRepositoryPort
) {
    fun createAnalysisUseCase() = CreateAnalysisUseCase(analysisRepo)
    fun getAllAnalysisByUserIdUseCase() = GetAllAnalysisByUserIdUseCase(analysisRepo)
    fun deleteAnalysisByIdUseCase() = DeleteAnalysisUseCase(analysisRepo)
}