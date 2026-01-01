package com.fit.exercises.presentation.controller

import com.fit.data.persistence.entity.UserRole
import com.fit.personal.domain.dto.GetUserByPersonalRequest
import com.fit.personal.domain.dto.GetUserByPersonalResponse
import com.fit.personal.domain.usecase.PersonalUseCaseFactory
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import jakarta.inject.Inject

@Controller("/personal")
class PersonalController(
    @Inject private val factory: PersonalUseCaseFactory
) {
    @Post("/students")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL)
    suspend fun signup(@Body req: GetUserByPersonalRequest): HttpResponse<GetUserByPersonalResponse> {
        val res = factory.getUserByPersonalIdUseCase().execute(req)
        return HttpResponse.ok(res)
    }
}