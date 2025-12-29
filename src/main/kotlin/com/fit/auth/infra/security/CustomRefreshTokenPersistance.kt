package com.fit.auth.infra.security

import com.fit.auth.infra.persistence.repository.RefreshTokenRepository
import com.fit.auth.infra.persistence.repository.UserRepository
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asPublisher
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Publisher
import java.time.Instant

@Singleton
@ExecuteOn(TaskExecutors.BLOCKING)
class CustomRefreshTokenPersistence(
    private val userRepository: UserRepository,
    private val refreshTokenRepository: RefreshTokenRepository
) : RefreshTokenPersistence {

    override fun persistToken(event: RefreshTokenGeneratedEvent?) {
        if (event?.refreshToken != null && event.authentication?.name != null) {
            runBlocking {
                userRepository.findByEmail(event.authentication.name)?.let {
                    refreshTokenRepository.save(
                        it.id!!,
                        event.refreshToken,
                        Instant.now().plusSeconds(3600)
                    )
                }
            }
        }
    }

    override fun getAuthentication(refreshToken: String): Publisher<Authentication> =
        flow {
            val token = refreshTokenRepository.findByTokenHash(refreshToken) ?: throw OauthErrorResponseException(
                INVALID_GRANT,
                "refresh token not found",
                null
            )
            token.revokedAt?.let {
                throw OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null)
            }
            emit(Authentication.build(token.userId.toString()))
        }.asPublisher()
}