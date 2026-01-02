package com.fit.diet.domain.usecase

import com.fit.diet.domain.ports.DietRepositoryPort
import com.fit.diet.presentation.controller.dto.CreateDietRequest
import com.fit.diet.presentation.controller.dto.CreateDietResponse
import com.fit.utils.toDietDTO

class CreateDietUseCase(
    private val dietRepo: DietRepositoryPort,
) {
    suspend fun execute(req: CreateDietRequest): CreateDietResponse {
        val response = dietRepo.save(content = req.content, req.userId, req.goal).toDietDTO()
        return CreateDietResponse(response)
    }
}
