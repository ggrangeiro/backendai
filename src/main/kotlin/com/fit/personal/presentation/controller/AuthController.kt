package com.fit.personal.presentation.controller

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


}