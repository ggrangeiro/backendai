package com.fit.data

import com.fit.data.persistence.entity.DietEntity
import com.fit.data.persistence.entity.DietGoalEntity
import com.fit.data.repository.DietRepository
import com.fit.diet.domain.model.Diet
import com.fit.diet.domain.ports.DietRepositoryPort
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

@Singleton
class DietRepositoryAdapter(
    private val dietRepo: DietRepository,
) : DietRepositoryPort {
    override suspend fun save(
        content: String,
        userId: Long,
        goal: DietGoalEntity
    ): Diet = dietRepo.save(content, userId, goal).toDomain()

    override suspend fun findAllByUserId(
        userId: Long,
    ): List<Diet> = dietRepo.findAll().map { it.toDomain() }.toList()

    override suspend fun findUserIdAndDietId(userId: Long, dietId: Long): Diet =
        dietRepo.findByIdAndUserId(id = dietId, userId = userId).toDomain()

    override suspend fun delete(userId: Long, dietId: Long): Boolean {
        try {
            dietRepo.deleteById(dietId)
            return true
        } catch (_: Exception) {
            return false
        }
    }
}

private fun DietEntity.toDomain(): Diet =
    Diet(
        id = this.id!!,
        content = this.content,
        goal = this.goal
    )
