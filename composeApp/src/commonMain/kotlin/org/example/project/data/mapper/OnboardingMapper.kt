package org.example.project.data.mapper

import org.example.project.data.dto.OnBoardingDto
import org.example.project.domain.entity.OnboardingItem

fun OnBoardingDto.toEntity() : OnboardingItem =
    OnboardingItem(
        imageRes = imageUrl,
        title = title,
        description = description
    )