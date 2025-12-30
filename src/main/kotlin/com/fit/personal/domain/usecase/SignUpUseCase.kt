package com.fit.personal.domain.usecase

import com.fit.auth.domain.dto.SignUpRequest
import com.fit.auth.domain.dto.SignUpResponse
import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.UserRepositoryPort

class SignUpUseCase(
    private val userRepo: UserRepositoryPort,
    private val passwordHasher: PasswordHasherPort
) {
    suspend fun execute(req: SignUpRequest): SignUpResponse {


        return SignUpResponse(
          "  user.email",
            "user.passwordHash"
        )
    }
}