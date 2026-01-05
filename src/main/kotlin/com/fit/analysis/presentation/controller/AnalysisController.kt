package com.fit.analysis.presentation.controller

import com.fit.analysis.domain.usecase.AnalysisUseCaseFactory
import com.fit.analysis.presentation.controller.dto.CreateAnalysisRequest
import com.fit.analysis.presentation.controller.dto.CreateAnalysisResponse
import com.fit.analysis.presentation.controller.dto.DeleteAnalysisRequest
import com.fit.analysis.presentation.controller.dto.DeleteAnalysisResponse
import com.fit.analysis.presentation.controller.dto.GetAllAnalysisResponse
import com.fit.data.persistence.entity.UserRole
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.security.annotation.Secured
import jakarta.inject.Inject

@Controller("/analysis")
class AnalysisController(
    @Inject private val factory: AnalysisUseCaseFactory
) {
    @Post("/")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_USER)
    suspend fun createAnalysis(@Body req: CreateAnalysisRequest): HttpResponse<CreateAnalysisResponse> {
        val res = factory.createAnalysisUseCase().execute(req)
        return HttpResponse.ok(res)
    }

    @Get("/{id}")
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun getAllAnalysisByUser(id: Long): HttpResponse<GetAllAnalysisResponse> {
        val res = factory.getAllAnalysisByUserIdUseCase().execute(id)
        return HttpResponse.ok(res)
    }

    @Delete
    @Secured(UserRole.ROLE_ADMIN, UserRole.ROLE_PERSONAL, UserRole.ROLE_USER)
    suspend fun deleteAnalysis(@Body req: DeleteAnalysisRequest): HttpResponse<DeleteAnalysisResponse> {
        val res = factory.deleteAnalysisByIdUseCase().execute(req)
        return HttpResponse.ok(res)
    }
}