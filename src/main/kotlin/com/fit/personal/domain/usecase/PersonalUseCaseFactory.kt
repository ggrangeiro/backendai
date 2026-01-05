package com.fit.personal.domain.usecase

import com.fit.auth.domain.ports.UserRepositoryPort
import jakarta.inject.Singleton

@Singleton
class PersonalUseCaseFactory(
    val userRepo: UserRepositoryPort
) {
    fun getUserByPersonalIdUseCase() = GetUserByPersonalIdUseCase(userRepo)
    fun getAddCreditsUseCaseUseCase() = AddCreditsUseCase(userRepo)
}