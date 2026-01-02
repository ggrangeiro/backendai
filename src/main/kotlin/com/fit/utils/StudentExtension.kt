package com.fit.utils

import com.fit.auth.domain.model.User
import com.fit.personal.presentation.controller.dto.StudentDTO

fun User.toStudentDTO() =
    StudentDTO(
        id = this.id,
        name = this.name,
        email = this.email
    )
