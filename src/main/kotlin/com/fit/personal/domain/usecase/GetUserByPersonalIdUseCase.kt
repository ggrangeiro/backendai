package com.fit.personal.domain.usecase

import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.personal.presentation.controller.dto.GetUserByPersonalRequest
import com.fit.personal.presentation.controller.dto.GetUserByPersonalResponse
import com.fit.utils.toStudentDTO
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class GetUserByPersonalIdUseCase(
    private val userRepo: UserRepositoryPort,
) {
    suspend fun execute(req: GetUserByPersonalRequest): GetUserByPersonalResponse {
        userRepo.findById(req.personalId) ?: throw HttpStatusException(
            HttpStatus.UNAUTHORIZED,
            "Usuário não registrado"
        )

        val response = userRepo.findUsersByPersonalId(req.personalId).mapNotNull {
            it!!.toStudentDTO()
        }
        return GetUserByPersonalResponse(response)
    }
}

