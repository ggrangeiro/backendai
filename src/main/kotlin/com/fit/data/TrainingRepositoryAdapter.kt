package com.fit.data

import com.fit.data.persistence.entity.TrainingEntity
import com.fit.data.persistence.entity.TrainingGoalEntity
import com.fit.data.repository.TrainingRepository
import com.fit.training.domain.model.Training
import com.fit.training.domain.ports.TrainingRepositoryPort
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

@Singleton
class TrainingRepositoryAdapter(
    private val trainingRepo: TrainingRepository,
) : TrainingRepositoryPort {
    override suspend fun save(
        content: String,
        userId: Long,
        goal: TrainingGoalEntity
    ): Training = trainingRepo.save(content, userId, goal).toDomain()

    override suspend fun findAllByUserId(
        userId: Long,
    ): List<Training> = trainingRepo.findAll().map { it.toDomain() }.toList()

    override suspend fun findUserIdAndTrainingId(userId: Long, trainingId: Long): Training =
        trainingRepo.findByIdAndUserId(id = trainingId, userId = userId).toDomain()

    override suspend fun delete(userId: Long, trainingId: Long) = trainingRepo.deleteById(trainingId)
}

private fun TrainingEntity.toDomain(): Training =
    Training(
        id = this.id!!,
        content = this.content,
        goal = this.goal
    )
