package com.fit.utils

import com.fit.exercises.domain.model.Exercise
import com.fit.exercises.presentation.controller.dto.ExerciseDTO

fun Exercise.toExerciseDTO() =
    ExerciseDTO(
        id = this.id,
        name = this.name
    )