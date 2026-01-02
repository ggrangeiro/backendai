package com.fit.diet.domain.usecase

import com.fit.diet.domain.ports.DietRepositoryPort
import com.fit.diet.presentation.controller.dto.GetDietResponse
import com.fit.utils.toDietDTO

class GetDietByUserIdUseCase(
    private val dietRepo: DietRepositoryPort
) {
    suspend fun execute(userId: Long, dietId: Long): GetDietResponse {
        val response = dietRepo.findUserIdAndDietId(userId = userId, dietId = dietId).toDietDTO()
        return GetDietResponse(response)
    }
}