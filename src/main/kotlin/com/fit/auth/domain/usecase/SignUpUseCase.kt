package com.fit.auth.domain.usecase

import com.fit.auth.domain.dto.LoginResponse
import com.fit.auth.domain.dto.SignUpRequest
import com.fit.auth.domain.model.RefreshToken
import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.auth.domain.ports.RefreshTokenRepositoryPort
import com.fit.auth.domain.ports.TokenProviderPort
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.auth.utils.sha256Hex
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import java.time.Instant

class SignUpUseCase(
    private val userRepo: UserRepositoryPort,
    private val passwordHasher: PasswordHasherPort,
    private val tokenProvider: TokenProviderPort,
    private val refreshRepo: RefreshTokenRepositoryPort
) {
    suspend fun execute(req: SignUpRequest): LoginResponse {
        userRepo.findByEmail(req.email.trim().lowercase())?.let {
            throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Não pode cadastrar um user com o mesmo email")
        }

        val user = userRepo.saveUser(
            email = req.email.trim().lowercase(),
            name = req.name,
            password = passwordHasher.hashPassword(req.password)
        )
            ?: throw HttpStatusException(
                HttpStatus.UNAUTHORIZED,
                "Não foi possível criar essa conta"
            )

        val pair = tokenProvider.generateTokens(user)

        val tokenHash = pair.refreshToken.sha256Hex()
        refreshRepo.save(
            RefreshToken(
                id = 0,
                userId = user.id,
                tokenHash = tokenHash,
                expiresAt = pair.refreshTokenExpiresAt,
                revokedAt = null
            )
        )

        val expiresInSeconds = pair.accessTokenExpiresAt.epochSecond - Instant.now().epochSecond

        return LoginResponse(
            accessToken = pair.accessToken,
            refreshToken = pair.refreshToken,
            expiresInSeconds = expiresInSeconds.coerceAtLeast(0)
        )
    }
}