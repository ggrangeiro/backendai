package com.fit.auth.domain.usecase

import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.domain.ports.UserRepositoryPort
import jakarta.inject.Singleton

@Singleton
class AuthUseCaseFactory(
    val userRepo: UserRepositoryPort,
    val passwordHasher: PasswordHasherPort,
    val refreshRepo: RefreshTokenRepositoryPort
) {
    fun signUpUseCase() = SignUpUseCase(userRepo, passwordHasher)
    fun logoutUseCase() = LogoutUseCase(refreshRepo)
}