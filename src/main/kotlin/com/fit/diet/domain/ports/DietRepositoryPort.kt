package com.fit.diet.domain.ports

import com.fit.data.persistence.entity.DietGoalEntity
import com.fit.diet.domain.model.Diet

interface DietRepositoryPort {
    suspend fun save(content: String, userId: Long, goal: DietGoalEntity): Diet
    suspend fun findAllByUserId(userId: Long): List<Diet>
    suspend fun findUserIdAndDietId(userId: Long, dietId: Long): Diet
    suspend fun delete(userId: Long, dietId: Long): Boolean
}

