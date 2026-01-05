package com.fit.utils

import com.fit.analysis.domain.model.Analysis
import com.fit.analysis.presentation.controller.dto.AnalysisDTO
import com.fit.data.persistence.entity.AnalysisEntity

fun AnalysisEntity.toAnalysisModel(): Analysis = Analysis(
    id = this.id ?: 0,
    userId = this.userId,
    type = this.type,
    createdAt = this.createdAt,
    result = this.result,

)

fun Analysis.toDTO(): AnalysisDTO {
    return AnalysisDTO(
        id = this.id,
        userId = this.userId,
        type = this.type,
        createAt = this.createdAt?.toEpochMilli(),

        result = this.result
    )
}
