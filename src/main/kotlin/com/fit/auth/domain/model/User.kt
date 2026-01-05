package com.fit.auth.domain.model

import com.fit.data.persistence.entity.UserRole

data class User(
    val id: Long,
    val email: String,
    val name: String,
    val passwordHash: String,
    val isActive: Boolean,
    val role: UserRole,
    val credits: Long? = null,
)