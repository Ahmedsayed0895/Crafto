package org.example.project.domain.repository

import org.example.project.domain.entity.OnboardingItem

interface OnboardingRepository {
    suspend fun getOnboardingData(): List<OnboardingItem>
}