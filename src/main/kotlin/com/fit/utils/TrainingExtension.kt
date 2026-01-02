package com.fit.utils

import com.fit.training.domain.model.Training
import com.fit.training.presentation.controller.dto.TrainingDTO

fun Training.toTrainingDTO() =
    TrainingDTO(
        id = this.id,
        content = this.content,
        goal = this.goal
    )