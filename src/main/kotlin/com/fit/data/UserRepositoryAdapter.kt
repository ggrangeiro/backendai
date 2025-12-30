package com.fit.data

import com.fit.auth.domain.model.User
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.data.repository.UserRepository
import com.fit.persistence.entity.UserEntity
import com.fit.persistence.entity.UserRole
import jakarta.inject.Singleton

@Singleton
class UserRepositoryAdapter(
    private val repo: UserRepository
) : UserRepositoryPort {

    override suspend fun findByEmail(email: String): User? =
        repo.findByEmail(email)?.toDomain()

    override suspend fun saveUser(
        email: String,
        name: String,
        password: String,
        role: UserRole,
    ): User = repo.save(email = email, name = name, password = password, role = role).toDomain()


    override suspend fun findById(id: Long): User? = repo.findById(id)?.toDomain()

    override suspend fun findUsersByPersonalId(id: Long): List<User?> {
        val userList = repo.findByPersonalId(id).mapNotNull { it?.toDomain() }
        return userList
    }

    private fun UserEntity.toDomain(): User =
        User(
            id = this.id!!,
            email = this.email,
            name = this.name,
            passwordHash = this.password,
            isActive = this.isActive,
            role = this.role
        )
}