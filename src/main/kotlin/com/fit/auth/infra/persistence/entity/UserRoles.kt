package com.fit.auth.infra.persistence.entity

enum class UserRole {
    USER, PERSONAL, ADMIN;

    companion object {
        const val ROLE_USER = "USER"
        const val ROLE_PERSONAL = "PERSONAL"
        const val ROLE_ADMIN = "ADMIN"
    }
}