package com.fit.auth.domain.usecase

import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.auth.presentation.controller.dto.SignUpRequest
import com.fit.auth.presentation.controller.dto.SignUpResponse
import com.fit.data.persistence.entity.UserRole
import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class SignUpUseCase(
    private val userRepo: UserRepositoryPort,
    private val passwordHasher: PasswordHasherPort,
    private val exerciseRepo: ExerciseRepositoryPort,
) {
    suspend fun execute(req: SignUpRequest, userRole: UserRole): SignUpResponse {
        userRepo.findByEmail(req.email.trim().lowercase())?.let {
            throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Não pode cadastrar um user com o mesmo email")
        }
        val user = userRepo.saveUser(
            email = req.email.trim().lowercase(),
            name = req.name,
            password = passwordHasher.hashPassword(req.password),
            role = userRole

        ) ?: throw HttpStatusException(
            HttpStatus.UNAUTHORIZED,
            "Não foi possível criar essa conta"
        )

        val exercises = exerciseRepo.findAll()
        exercises.forEach { exercise ->
            exerciseRepo.assignExerciseToUser(exerciseId = exercise.id, userId = user.id)
        }
        return SignUpResponse(
            user.email
        )
    }
}