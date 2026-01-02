package com.fit.diet.domain.usecase

import com.fit.diet.domain.ports.DietRepositoryPort
import jakarta.inject.Singleton

@Singleton
class DietUseCaseFactory(
    val dietRepo: DietRepositoryPort
) {
    fun createDietUseCase() = CreateDietUseCase(dietRepo)
    fun getDietByUserIdUseCase() = GetDietByUserIdUseCase(dietRepo)
    fun getAllDietsByUserIdUseCase() = GetAllDietsByUserIdUseCase(dietRepo)
    fun deleteDietByIdUseCase() = DeleteDietUseCase(dietRepo)
}