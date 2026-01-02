@file:JvmName("GetAllExercisesUseCaseKt")

package com.fit.diet.domain.usecase

import com.fit.diet.domain.ports.DietRepositoryPort
import com.fit.diet.presentation.controller.dto.GetAllDietsResponse
import com.fit.utils.toDietDTO

class GetAllDietsByUserIdUseCase(
    private val dietRepo: DietRepositoryPort
) {
    suspend fun execute(id: Long): GetAllDietsResponse {
        val diet = dietRepo.findAllByUserId(id)

        val response = diet.map { it.toDietDTO() }

        return GetAllDietsResponse(response)
    }
}