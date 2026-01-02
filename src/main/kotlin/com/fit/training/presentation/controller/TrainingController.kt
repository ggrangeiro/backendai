package com.fit.training.presentation.controller

import com.fit.data.persistence.entity.UserRole
import com.fit.training.domain.usecase.TrainingUseCaseFactory
import com.fit.training.presentation.controller.dto.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import jakarta.inject.Inject

@Controller("/training")
class TrainingController(
    @Inject private val factory: TrainingUseCaseFactory
) {
    @Post("/")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_USER)
    suspend fun createTraining(@Body req: CreateTrainingRequest): HttpResponse<CreateTrainingResponse> {
        val res = factory.createTrainingUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/{id}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun getAllTrainingByUser(id: Long): HttpResponse<GetAllTrainingsResponse> {
        val res = factory.getAllTrainingsByUserIdUseCase().execute(id)
        return HttpResponse.ok(res)
    }

    @Delete
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun deleteTraining(@Body req: DeleteTrainingRequest): HttpResponse<DeleteTrainingResponse> {
        val res = factory.deleteTrainingByIdUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/{trainingId}/{userId}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL)
    suspend fun getTrainingIdByUserId(trainingId: Long, userId: Long): HttpResponse<GetTrainingResponse> {
        val res = factory.getTrainingByUserIdUseCase().execute(userId = userId, trainingId = trainingId)
        return HttpResponse.ok(res)
    }
}