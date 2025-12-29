package com.fit.auth.domain.ports

interface PasswordHasherPort {
    fun matches(rawPassword: String, passwordHash: String): Boolean
    fun hashPassword(rawPassword: String): String
}