package com.fit.auth.domain.usecase

import com.fit.auth.domain.dto.LogoutRequest
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.utils.sha256Hex
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.Instant

class LogoutUseCase(
    private val refreshRepo: RefreshTokenRepositoryPort
) {
    suspend fun execute(req: LogoutRequest) {
        val tokenHash = req.refreshToken.sha256Hex()
        val revoked = refreshRepo.revokeByTokenHash(tokenHash, Instant.now())
        if (!revoked) {
            throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Refresh token inválido")
        }
    }
}
