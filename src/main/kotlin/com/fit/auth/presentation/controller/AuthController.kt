package com.fit.auth.presentation.controller

import com.fit.auth.domain.dto.LogoutRequest
import com.fit.auth.domain.dto.SignUpRequest
import com.fit.auth.domain.dto.SignUpResponse
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
    @Post("/signup")
    suspend fun signup(@Body req: SignUpRequest): HttpResponse<SignUpResponse> {
        val res = factory.signUpUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Post("/logout")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    suspend fun logout(@Body req: LogoutRequest): HttpResponse<Any> {
        factory.logoutUseCase().execute(req)
        return HttpResponse.noContent()
    }
}