package com.fit.auth.domain.model

data class User(
    val id: Long,
    val email: String,
    val passwordHash: String,
    val isActive: Boolean
)