package com.fit.auth.domain.usecase

import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.exercises.domain.ports.ExerciseRepositoryPort
import jakarta.inject.Singleton

@Singleton
class AuthUseCaseFactory(
    val userRepo: UserRepositoryPort,
    val passwordHasher: PasswordHasherPort,
    val exerciseRepo: ExerciseRepositoryPort
) {
    fun signUpUseCase() = SignUpUseCase(userRepo, passwordHasher, exerciseRepo)
}