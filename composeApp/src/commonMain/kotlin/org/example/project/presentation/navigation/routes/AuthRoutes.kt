package org.example.project.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.example.project.domain.entity.UserType
import org.example.project.presentation.navigation.CraftsmanSetupDestination
import org.example.project.presentation.navigation.CustomerSetupDestination
import org.example.project.presentation.navigation.OnboardingDestination
import org.example.project.presentation.navigation.OtpRegistrationDestination
import org.example.project.presentation.navigation.SplashDestination
import org.example.project.presentation.navigation.UserTypeSelectionDestination
import org.example.project.presentation.screens.auth.UserTypeSelectionScreen
import org.example.project.presentation.screens.onboarding.OnboardingScreen
import org.example.project.presentation.screens.register.RegisterScreen
import org.example.project.presentation.screens.splash.SplashScreen

fun NavGraphBuilder.splashRoute(navController: NavHostController) {
    composable<SplashDestination> {
        SplashScreen(
            onTimeout = {
                navController.navigate(OnboardingDestination) {
                    popUpTo(SplashDestination) { inclusive = true }
                }
            }
        )
    }
}

fun NavGraphBuilder.onboardingRoute(navController: NavHostController) {
    composable<OnboardingDestination> {
        OnboardingScreen(
            onNavigateToOtp = {
                navController.navigate(OtpRegistrationDestination) {
                    popUpTo(OnboardingDestination) { inclusive = true }
                }
            }
        )
    }
}

fun NavGraphBuilder.otpRegistrationRoute(navController: NavHostController) {
    composable<OtpRegistrationDestination> {
        RegisterScreen(
            onRegistrationComplete = {
                navController.navigate(UserTypeSelectionDestination) {
                    popUpTo(OtpRegistrationDestination) { inclusive = true }
                }
            },
        )
    }
}

fun NavGraphBuilder.userTypeSelectionRoute(
    navController: NavHostController,
    onUserTypeUpdated: (UserType) -> Unit
) {
    composable<UserTypeSelectionDestination> {
        UserTypeSelectionScreen(
            onNavigateToSetup = { userType ->
                onUserTypeUpdated(userType)

                when (userType) {
                    UserType.CRAFTSMAN -> {
                        navController.navigate(CraftsmanSetupDestination) {
                            popUpTo(UserTypeSelectionDestination) { inclusive = true }
                        }
                    }
                    UserType.CUSTOMER -> {
                        navController.navigate(CustomerSetupDestination) {
                            popUpTo(UserTypeSelectionDestination) { inclusive = true }
                        }
                    }
                }
            },
        )
    }
}