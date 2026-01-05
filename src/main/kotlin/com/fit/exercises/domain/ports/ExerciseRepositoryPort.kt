package com.fit.exercises.domain.ports

import com.fit.data.persistence.entity.ExerciseCategoryEntity
import com.fit.exercises.domain.model.Exercise

interface ExerciseRepositoryPort {
    suspend fun saveExercise(name: String, category: ExerciseCategoryEntity): Exercise
    suspend fun updateExercise(id: Long, name: String, category: ExerciseCategoryEntity): Exercise
    suspend fun assignExerciseToUser(exerciseId: Long, userId: Long): Boolean
    suspend fun findByName(name: String): Exercise?
    suspend fun findByUserId(id: Long): List<Exercise>
    suspend fun findAll(): List<Exercise>
}