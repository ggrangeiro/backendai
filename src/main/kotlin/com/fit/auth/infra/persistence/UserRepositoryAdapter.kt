package com.fit.auth.infra.persistence

import com.fit.auth.domain.model.User
import com.fit.auth.domain.ports.UserRepositoryPort
import com.fit.auth.infra.persistence.entity.UserEntity
import com.fit.auth.infra.persistence.repository.UserRepository
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
        password: String
    ): User = repo.save(email = email, name = name, password = password).toDomain()


    override suspend fun findById(id: Long): User? = repo.findById(id)?.toDomain()

    private fun UserEntity.toDomain(): User =
        User(
            id = this.id!!,
            email = this.email,
            name = this.name,
            passwordHash = this.password,
            isActive = this.isActive
        )
}
