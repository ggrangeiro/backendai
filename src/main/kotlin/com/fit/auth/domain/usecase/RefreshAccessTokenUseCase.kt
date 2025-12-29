package com.fit.auth.domain.usecase

import com.fit.auth.domain.dto.RefreshRequest
import com.fit.auth.domain.dto.RefreshResponse
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.domain.ports.TokenProviderPort
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.auth.utils.sha256Hex
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.Instant

class RefreshAccessTokenUseCase(
    private val refreshRepo: RefreshTokenRepositoryPort,
    private val userRepo: UserRepositoryPort,
    private val tokenProvider: TokenProviderPort
) {
    suspend fun execute(req: RefreshRequest): RefreshResponse {
        val tokenHash = req.refreshToken.sha256Hex()
        val token = refreshRepo.findByTokenHash(tokenHash)
            ?: throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Refresh token inválido")

        val now = Instant.now()
        if (token.isRevoked() || token.isExpired(now)) {
            throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Refresh token expirado ou revogado")
        }

        val user = userRepo.findById(token.userId)
            ?: throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado")

        if (!user.isActive) throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Usuário inativo")

        val (access, exp) = tokenProvider.generateAccessToken(user)
        val expiresInSeconds = exp.epochSecond - now.epochSecond

        return RefreshResponse(accessToken = access, expiresInSeconds = expiresInSeconds.coerceAtLeast(0))
    }
}
