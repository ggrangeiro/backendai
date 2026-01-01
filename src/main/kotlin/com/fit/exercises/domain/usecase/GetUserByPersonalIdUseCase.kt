package com.fit.exercises.domain.usecase

import com.fit.auth.domain.model.User
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.personal.domain.dto.GetUserByPersonalRequest
import com.fit.personal.domain.dto.GetUserByPersonalResponse
import com.fit.personal.domain.dto.StudentDTO
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

private fun User.toStudentDTO() =
    StudentDTO(
        id = this.id,
        name = this.name,
        email = this.email
    )
