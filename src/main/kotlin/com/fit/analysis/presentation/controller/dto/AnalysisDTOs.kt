package com.fit.analysis.presentation.controller.dto

import com.fit.data.persistence.entity.AnalysisResultEntity
import com.fit.data.persistence.entity.AnalysisType
import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class AnalysisDTO(
    val id: Long? = null,
    val userId: Long,
    val createAt: Long? = null,
    val type: AnalysisType,
    val result: AnalysisResultEntity
)

@Serdeable
data class CreateAnalysisRequest(val analysis: AnalysisDTO)

@Serdeable
data class CreateAnalysisResponse(val analysis: AnalysisDTO)

@Serdeable
data class DeleteAnalysisRequest(val userId: Long, val id: Long)

@Serdeable
data class DeleteAnalysisResponse(val success: Boolean)

@Serdeable
data class GetAllAnalysisResponse(val analysis: List<AnalysisDTO>)

@Serdeable
data class GetAnalysisResponse(val analysis: AnalysisDTO)

