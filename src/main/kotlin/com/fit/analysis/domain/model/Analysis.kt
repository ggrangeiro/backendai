package com.fit.analysis.domain.model

import com.fit.data.persistence.entity.AnalysisResultEntity
import com.fit.data.persistence.entity.AnalysisType
import java.time.Instant

data class Analysis(
    val id: Long,
    val userId: Long,
    val type: AnalysisType,
    val createdAt: Instant?,
    val result: AnalysisResultEntity
)