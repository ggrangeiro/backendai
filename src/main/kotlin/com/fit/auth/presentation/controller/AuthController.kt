package com.fit.auth.presentation.controller

import com.fit.auth.domain.dto.*
import com.fit.auth.domain.usecase.AuthUseCaseFactory
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject

@Controller("/exercicio")
class AuthController(
    @Inject private val factory: AuthUseCaseFactory
) {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/login")
    suspend fun login(@Body req: LoginRequest): HttpResponse<LoginResponse> {
        val res = factory.loginUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Post("/logout")
    @Secured(SecurityRule.IS_ANONYMOUS)
    suspend fun logout(@Body req: LogoutRequest): HttpResponse<Any> {
        factory.logoutUseCase().execute(req)
        return HttpResponse.noContent()
    }

    @Post("/refresh")
    suspend fun refresh(@Body req: RefreshRequest): HttpResponse<RefreshResponse> {
        val res = factory.refreshUseCase().execute(req)
        return HttpResponse.ok(res)
    }
}