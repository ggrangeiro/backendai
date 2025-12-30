package com.fit.auth.domain.usecase

import com.fit.auth.domain.dto.SignUpRequest
import com.fit.auth.domain.dto.SignUpResponse
import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.persistence.entity.UserRole
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class SignUpUseCase(
    private val userRepo: UserRepositoryPort,
    private val passwordHasher: PasswordHasherPort
) {
    suspend fun execute(req: SignUpRequest): SignUpResponse {
        userRepo.findByEmail(req.email.trim().lowercase())?.let {
            throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Não pode cadastrar um user com o mesmo email")
        }
        val user = userRepo.saveUser(
            email = req.email.trim().lowercase(),
            name = req.name,
            password = passwordHasher.hashPassword(req.password),
            role = UserRole.USER

        ) ?: throw HttpStatusException(
            HttpStatus.UNAUTHORIZED,
            "Não foi possível criar essa conta"
        )

        return SignUpResponse(
            user.email,
            user.passwordHash
        )
    }
}