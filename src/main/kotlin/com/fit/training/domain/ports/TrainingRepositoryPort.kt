package com.fit.training.domain.ports

import com.fit.data.persistence.entity.TrainingGoalEntity
import com.fit.training.domain.model.Training

interface TrainingRepositoryPort {
    suspend fun save(content: String, userId: Long, goal: TrainingGoalEntity): Training
    suspend fun findAllByUserId(userId: Long): List<Training>
    suspend fun findUserIdAndTrainingId(userId: Long, trainingId: Long): Training
    suspend fun delete(userId: Long, trainingId: Long): Int
}

