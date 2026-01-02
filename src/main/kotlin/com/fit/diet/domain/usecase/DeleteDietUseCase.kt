package com.fit.diet.domain.usecase

import com.fit.diet.domain.ports.DietRepositoryPort
import com.fit.diet.presentation.controller.dto.DeleteDietRequest
import com.fit.diet.presentation.controller.dto.DeleteDietResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class DeleteDietUseCase(
    private val dietRepo: DietRepositoryPort,
) {
    suspend fun execute(req: DeleteDietRequest): DeleteDietResponse {

        try {
            dietRepo.delete(userId = req.userId, dietId = req.id)
            return DeleteDietResponse(true)
        } catch (_: Exception) {
            throw HttpStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Diet can not be deleted"
            )
        }
    }
}
