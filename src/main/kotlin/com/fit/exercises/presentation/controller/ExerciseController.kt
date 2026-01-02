package com.fit.exercises.presentation.controller

import com.fit.data.persistence.entity.UserRole
import com.fit.exercises.domain.usecase.ExerciseUseCaseFactory
import com.fit.exercises.presentation.controller.dto.*
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import jakarta.inject.Inject

@Controller("/exercises")
class ExerciseController(
    @Inject private val factory: ExerciseUseCaseFactory
) {
    @Post("/")
    @Secured(UserRole.ROLE_ADMIN)
    suspend fun createExercise(@Body req: CreateExerciseRequest): HttpResponse<CreateExerciseResponse> {
        val res = factory.createExerciseUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun getAllExercises(): HttpResponse<GetAllExercisesResponse> {
        val res = factory.getAllExercisesUseCase().execute()
        return HttpResponse.ok(res)
    }

    @Get("/{id}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun getExercisesByUserId(id: Long): HttpResponse<GetExerciseByUserResponse> {
        val res = factory.getExercisesByUserIdUseCase().execute(id)
        return HttpResponse.ok(res)
    }

    @Post("/assign")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL)
    suspend fun assignExerciseToUser(@Body req: AssignExerciseRequest): HttpResponse<AssignExerciseResponse> {
        val res = factory.assignExerciseUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    // Ajustar esse endpoint dps
    @Put("/")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun updateExercises(@Body req: EditExerciseRequest): HttpResponse<EditExerciseResponse> {
        val res = factory.editExerciseByIdUseCase().execute(req)
        return HttpResponse.ok(res)
    }
}