package com.fit.auth.domain.ports

import com.fit.auth.domain.model.User
import com.fit.persistence.entity.UserRole

interface UserRepositoryPort {
    suspend fun findByEmail(email: String): User?
    suspend fun saveUser(email: String, name: String, password: String, role: UserRole): User?
    suspend fun findById(id: Long): User?
    suspend fun findUsersByPersonalId(id: Long): List<User?>
}