package com.fit.auth.domain.ports

import com.fit.auth.domain.model.User

interface UserRepositoryPort {
    suspend fun findByEmail(email: String): User?
    suspend fun findById(id: Long): User?
}