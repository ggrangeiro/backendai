package com.fit.utils

import com.fit.diet.domain.model.Diet
import com.fit.diet.presentation.controller.dto.DietDTO

fun Diet.toDietDTO() =
    DietDTO(
        id = this.id,
        content = this.content,
        goal = this.goal
    )