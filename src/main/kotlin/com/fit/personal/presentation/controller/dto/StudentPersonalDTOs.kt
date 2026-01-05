package com.fit.personal.presentation.controller.dto

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class GetUserByPersonalRequest(val userId: Long, val personalId: Long)

@Serdeable
data class GetUserByPersonalResponse(val user: List<StudentDTO?>)

@Serdeable
data class StudentDTO(
    val id: Long,
    val name: String,
    val email: String,
)

@Serdeable
data class GiveCreditsRequest(val userId: Long, val amount: Long)

@Serdeable
data class GiveCreditsResponse(val success: Boolean)