package org.example.project.presentation.screens.onboarding

interface OnboardingScreenEffect {
    object NavigateToNext : OnboardingScreenEffect
    object NavigateToGetStartedScreen : OnboardingScreenEffect
    object NavigateToRegisterScreen : OnboardingScreenEffect
}