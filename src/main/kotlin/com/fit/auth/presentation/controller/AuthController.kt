package com.fit.auth.presentation.controller

import com.fit.auth.presentation.controller.dto.LogoutRequest
import com.fit.auth.presentation.controller.dto.SignUpRequest
import com.fit.auth.presentation.controller.dto.SignUpResponse
import com.fit.auth.domain.usecase.AuthUseCaseFactory
import com.fit.data.persistence.entity.UserRole
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import jakarta.inject.Inject

@Controller("/auth")
class AuthController(
    @Inject private val factory: AuthUseCaseFactory
) {

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/signup")
    suspend fun signup(@Body req: SignUpRequest): HttpResponse<SignUpResponse> {
        val res = factory.signUpUseCase().execute(req, UserRole.USER)
        return HttpResponse.ok(res)
    }

    @Secured(UserRole.ROLE_PERSONAL)
    @Post("/personal/signup")
    suspend fun studentSignUp(@Body req: SignUpRequest): HttpResponse<SignUpResponse> {
        val res = factory.signUpUseCase().execute(req, UserRole.USER)
        return HttpResponse.ok(res)
    }

    @Secured(UserRole.ROLE_ADMIN)
    @Post("/personal/signup")
    suspend fun personalSignUp(@Body req: SignUpRequest): HttpResponse<SignUpResponse> {
        val res = factory.signUpUseCase().execute(req, UserRole.PERSONAL)
        return HttpResponse.ok(res)
    }

    @Post("/logout")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    suspend fun logout(@Body req: LogoutRequest): HttpResponse<Any> {
        factory.logoutUseCase().execute(req)
        return HttpResponse.noContent()
    }
}