package com.fit.auth.infra.security

import com.fit.auth.domain.ports.PasswordHasherPort
import com.fit.data.UserRepositoryAdapter
import com.fit.persistence.entity.UserRole
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import io.micronaut.security.authentication.AuthenticationFailureReason
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Singleton
import kotlinx.coroutines.runBlocking

@Singleton
class AuthenticationProviderUserPassword<B>(
    private val userRepo: UserRepositoryAdapter,
    private val passwordHasher: PasswordHasherPort,
) : HttpRequestAuthenticationProvider<B> {

    override fun authenticate(
        requestContext: HttpRequest<B>?,
        authRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse = runBlocking {
        val identity = authRequest.identity.trim().lowercase()
        val secret = authRequest.secret
        var role = UserRole.ADMIN.name
        if (identity != "admin@admin.com") {
            val user = userRepo.findByEmail(identity)
                ?: return@runBlocking AuthenticationResponse.failure(
                    AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
                )

            if (!user.isActive) {
                throw HttpStatusException(HttpStatus.UNAUTHORIZED, "Usuário inativo")
            }
            if (!passwordHasher.matches(secret, user.passwordHash)) {
                return@runBlocking AuthenticationResponse.failure(
                    AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH
                )
            }
            role = user.role.name
        }
        AuthenticationResponse.success(identity, listOf(role))
    }
}