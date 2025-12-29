package com.fit.auth.domain.model

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val passwordHash: String,
    val isActive: Boolean
)