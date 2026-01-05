package com.fit.data

import com.fit.analysis.domain.model.Analysis
import com.fit.analysis.domain.ports.AnalysisRepositoryPort
import com.fit.data.persistence.entity.AnalysisEntity
import com.fit.data.repository.AnalysisRepository
import com.fit.data.repository.UserRepository
import com.fit.utils.toAnalysisModel
import jakarta.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class AnalysisRepositoryAdapter(
    private val analysisRepo: AnalysisRepository,
    private val userRepo: UserRepository,
) : AnalysisRepositoryPort {
    override suspend fun save(analysisEntity: AnalysisEntity): Analysis {
        val analysis = withContext(Dispatchers.IO) {
            analysisRepo.save(analysisEntity)
        }.toAnalysisModel()
        userRepo.decreaseCreditsIfSufficient(analysisEntity.userId, amount = 150)
        return analysis
    }

    override suspend fun findAllByUserId(userId: Long): List<Analysis> =
        analysisRepo.findByUserId(userId).map { it.toAnalysisModel() }.toList()

    override suspend fun delete(userId: Long, analysisId: Long): Boolean {
        TODO("Not yet implemented")
    }
}
