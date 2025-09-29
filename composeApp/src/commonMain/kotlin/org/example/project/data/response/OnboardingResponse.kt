package org.example.project.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.data.dto.OnBoardingDto

@Serializable
data class OnboardingResponse(
    @SerialName("onboarding_data")
    val onboardingData : List<OnBoardingDto>
)
