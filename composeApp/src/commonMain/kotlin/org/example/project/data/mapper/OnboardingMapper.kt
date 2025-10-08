package org.example.project.data.mapper

import org.example.project.data.dto.OnboardingDto
import org.example.project.domain.entity.OnboardingItem

fun OnboardingDto.toEntity() : OnboardingItem =
    OnboardingItem(
        imageRes = imageUrl,
        title = title,
        description = description
    )