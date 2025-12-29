package com.fit.auth.domain.usecase

import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.domain.ports.TokenProviderPort
import com.fit.auth.domain.ports.UserRepositoryPort
import jakarta.inject.Singleton

@Singleton
class AuthUseCaseFactory(
    val userRepo: UserRepositoryPort,
    val passwordHasher: PasswordHasherPort,
    val tokenProvider: TokenProviderPort,
    val refreshRepo: RefreshTokenRepositoryPort
) {
    fun loginUseCase() = LoginUseCase(userRepo, passwordHasher, tokenProvider, refreshRepo)
    fun logoutUseCase() = LogoutUseCase(refreshRepo)
    fun refreshUseCase() = RefreshAccessTokenUseCase(refreshRepo, userRepo, tokenProvider)
}