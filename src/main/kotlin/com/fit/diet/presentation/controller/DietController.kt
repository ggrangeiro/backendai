package com.fit.diet.presentation.controller

import com.fit.data.persistence.entity.UserRole
import com.fit.diet.domain.usecase.DietUseCaseFactory
import com.fit.diet.presentation.controller.dto.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import jakarta.inject.Inject

@Controller("/diet")
class DietController(
    @Inject private val factory: DietUseCaseFactory
) {
    @Post("/")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_USER)
    suspend fun createDiet(@Body req: CreateDietRequest): HttpResponse<CreateDietResponse> {
        val res = factory.createDietUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/{id}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun getAllDietByUser(id: Long): HttpResponse<GetAllDietsResponse> {
        val res = factory.getAllDietsByUserIdUseCase().execute(id)
        return HttpResponse.ok(res)
    }

    @Delete
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun deleteDiet(@Body req: DeleteDietRequest): HttpResponse<DeleteDietResponse> {
        val res = factory.deleteDietByIdUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/{dietId}/{userId}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL)
    suspend fun getDietByUserId(dietId: Long, userId: Long): HttpResponse<GetDietResponse> {
        val res = factory.getDietByUserIdUseCase().execute(userId = userId, dietId = dietId)
        return HttpResponse.ok(res)
    }
}