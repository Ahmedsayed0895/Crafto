package org.example.project.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationBarDestinations {
    @Serializable
    data object HomeScreen : NavigationBarDestinations

    @Serializable
    data object MyRequestsScreen : NavigationBarDestinations // Customer

    @Serializable
    data object MyJobsScreen : NavigationBarDestinations // Craftsman

    @Serializable
    data object MessagesScreen : NavigationBarDestinations

    @Serializable
    data object MoreScreen : NavigationBarDestinations
}

val bottomNavBarDestinationsMap = mapOf(
    NavigationBarDestinations.HomeScreen::class.qualifiedName to NavigationBarDestinations.HomeScreen,
    NavigationBarDestinations.MyRequestsScreen::class.qualifiedName to NavigationBarDestinations.MyRequestsScreen,
    NavigationBarDestinations.MyJobsScreen::class.qualifiedName to NavigationBarDestinations.MyJobsScreen,
    NavigationBarDestinations.MessagesScreen::class.qualifiedName to NavigationBarDestinations.MessagesScreen,
    NavigationBarDestinations.MoreScreen::class.qualifiedName to NavigationBarDestinations.MoreScreen,
    //SettingsDetailDestination::class.qualifiedName to NavigationBarDestinations.MoreScreen // Keep bottom nav selected
)

// ===== Auth Flow Destinations =====
@Serializable
data object SplashDestination

@Serializable
data object OnboardingDestination

@Serializable
data object OtpRegistrationDestination

@Serializable
data object UserTypeSelectionDestination

// ===== Setup Destinations =====
@Serializable
data object CraftsmanSetupDestination

@Serializable
data object CustomerSetupDestination

//@Serializable
//data class SettingsDetailDestination(val settingType: String)

// ============= MAIN APP (With Bottom Nav) =============
@Serializable
data object MainRoute // Parent for bottom nav

// ============= INITIAL ROUTE =============
@Serializable
data object SplashRoute