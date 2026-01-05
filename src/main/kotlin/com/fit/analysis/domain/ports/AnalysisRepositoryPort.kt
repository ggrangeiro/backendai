package com.fit.analysis.domain.ports

import com.fit.analysis.domain.model.Analysis
import com.fit.data.persistence.entity.AnalysisEntity

interface AnalysisRepositoryPort {
    suspend fun save(analysisEntity: AnalysisEntity): Analysis
    suspend fun findAllByUserId(userId: Long): List<Analysis>
    suspend fun delete(userId: Long, analysisId: Long): Boolean
}

