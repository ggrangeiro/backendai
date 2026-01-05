package com.fit.personal.domain.usecase

import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.personal.presentation.controller.dto.GiveCreditsRequest
import com.fit.personal.presentation.controller.dto.GiveCreditsResponse

class AddCreditsUseCase(
    private val userRepo: UserRepositoryPort,
) {
    suspend fun execute(req: GiveCreditsRequest): GiveCreditsResponse {
        userRepo.addCredits(req.userId, req.amount)
        return GiveCreditsResponse(true)
    }
}

