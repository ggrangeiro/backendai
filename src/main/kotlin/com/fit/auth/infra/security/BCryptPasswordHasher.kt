package com.fit.auth.infra.security

import com.fit.auth.domain.ports.PasswordHasherPort
import jakarta.inject.Singleton
import org.mindrot.jbcrypt.BCrypt

@Singleton
class BCryptPasswordHasher : PasswordHasherPort {
    override fun matches(rawPassword: String, passwordHash: String): Boolean =
        BCrypt.checkpw(rawPassword, passwordHash)

    override fun hashPassword(rawPassword: String): String = BCrypt.hashpw(rawPassword, BCrypt.gensalt(10))
}