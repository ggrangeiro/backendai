package com.fit.data

import com.fit.data.persistence.entity.ExerciseEntity
import com.fit.data.persistence.entity.UserExerciseEntity
import com.fit.data.persistence.entity.UserExerciseId
import com.fit.data.repository.ExerciseRepository
import com.fit.data.repository.UserExerciseRepository
import com.fit.exercises.domain.model.Exercise
import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

@Singleton
class ExerciseRepositoryAdapter(
    private val exerciseRepo: ExerciseRepository,
    private val userExerciseRepo: UserExerciseRepository,
) : ExerciseRepositoryPort {
    override suspend fun saveExercise(name: String): Exercise = exerciseRepo.save(name).toDomain()
    override suspend fun updateExercise(id: Long, name: String): Exercise =
        exerciseRepo.save(name = name, id = id).toDomain()

    override suspend fun findByName(name: String): Exercise? = exerciseRepo.findByName(name)?.toDomain()
    override suspend fun findByUserId(id: Long): List<Exercise> =
        userExerciseRepo.findByIdUserId(id).mapIndexed { index, dto ->
            Exercise(index.toLong()+1, dto.exerciseName)
        }

    override suspend fun assignExerciseToUser(exerciseId: Long, userId: Long): Boolean {
        val id = UserExerciseId(userId = userId, exerciseId = exerciseId)
        val exercise = UserExerciseEntity(id = id)
        try {
            userExerciseRepo.save(exercise)
            return true
        } catch (_: Exception) {
            return false
        }
    }

    override suspend fun findAll(): List<Exercise> = exerciseRepo.findAll().map { it.toDomain() }.toList()

    private fun ExerciseEntity.toDomain(): Exercise =
        Exercise(
            id = this.id!!,
            name = this.name
        )
}
