package com.fit.auth.infra.security

import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.handlers.LoginHandler
import io.micronaut.security.token.generator.AccessRefreshTokenGenerator
import io.micronaut.security.token.render.AccessRefreshToken
import jakarta.inject.Singleton
import java.util.*

@Singleton
@Replaces(LoginHandler::class)
class CustomLoginHandler(
    private val accessRefreshTokenGenerator: AccessRefreshTokenGenerator
) : LoginHandler<HttpRequest<*>, MutableHttpResponse<*>> {

    override fun loginSuccess(authentication: Authentication, request: HttpRequest<*>): MutableHttpResponse<*> {
        val tokenOptional: Optional<AccessRefreshToken> = accessRefreshTokenGenerator.generate(authentication)

        return if (tokenOptional.isPresent) {
            val accessTokenBean = tokenOptional.get()
            val userId = authentication.attributes["id"]?.toString() ?: ""
            val body = mutableMapOf<String, Any>(
                "access_token" to accessTokenBean.accessToken,
                "token_type" to "Bearer",
                "expires_in" to (accessTokenBean.expiresIn ?: 3600),
                "user_id" to userId
            )
            accessTokenBean.refreshToken?.let {
                body["refresh_token"] = it
            }
            HttpResponse.ok(body)
        } else {
            HttpResponse.serverError(mapOf("error" to "Falha na geração do token"))
        }
    }

    override fun loginRefresh(
        authentication: Authentication,
        refreshToken: String,
        request: HttpRequest<*>
    ): MutableHttpResponse<*> {
        val tokenOptional = accessRefreshTokenGenerator.generate(authentication)

        return if (tokenOptional.isPresent) {
            val accessTokenBean = tokenOptional.get()
            val body = mapOf(
                "access_token" to accessTokenBean.accessToken,
                "token_type" to "Bearer",
                "expires_in" to (accessTokenBean.expiresIn ?: 3600),
                "refresh_token" to (accessTokenBean.refreshToken ?: "")
            )
            HttpResponse.ok(body)
        } else {
            HttpResponse.unauthorized<Any>()
        }
    }

    override fun loginFailed(
        authenticationResponse: AuthenticationResponse,
        request: HttpRequest<*>
    ): MutableHttpResponse<*> {
        return HttpResponse.unauthorized<Any>()
    }
}